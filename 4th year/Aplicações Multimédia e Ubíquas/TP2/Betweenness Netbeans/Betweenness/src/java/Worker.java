/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JPVS
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Worker {
	
	public Worker() {}
	
	// Receive two points and a divisor
	// Returns list of intermediate points between two points
	// The number of intermediate points is given by div
	public List<String> haversine(Point p1, Point p2, int div) {
		int i;
		double x, y, avancaX, avancaY;
		List<String> points = new ArrayList<>();
		Point dif = new Point();
		dif = dif.diff(p1,p2);
		x = dif.getX() / div;
		y = dif.getY() / div;
		for(i = 1; i < div; i++) {
			avancaX = p1.getX() + (i*x);
			avancaY = p1.getY() + (i*y);
			points.add(new Point(avancaX, avancaY).toString());
		}
		return points;
	}
	
	// Receive coordinates
	// Return URL request for further use
	public String reverse_geocoding(String coordinates, String key) {
		StringBuilder s = new StringBuilder();
		s.append("https://maps.googleapis.com/maps/api/geocode/json?");
		s.append("latlng=" + coordinates);
		s.append("&key=" + key);
		return s.toString(); 
	}
	
	// Receive origin and destination coordinates
	// Return URL request for further use
	public String directions(String origin, String destination, String key) {
		StringBuilder s = new StringBuilder();
		s.append("https://maps.googleapis.com/maps/api/directions/json?");
		s.append("origin=" + origin);
		s.append("&destination=" + destination);
		s.append("&key=" + key + "\n");
		return s.toString();
	}
	
	// Receive a 'filename' and a string
	// Write the string to a file named 'filename'
	public void write_to_file(String filename, String content) throws IOException {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(content);
		}
	}
	
	// Read JSON file
	// Parse coordinates from that file and writes the result to a new file
	public void coord_from_json_to_file(String filename, String filename2) throws IOException {
		int i;
		Integer distance;
		JSONObject obj;
		JSONArray arr, legs, steps;
		StringBuilder sb1, sb2;
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line = br.readLine();
		    sb1 = new StringBuilder();
		    while(line != null) {
		    		sb1.append(line);
		    		line = br.readLine();
		    }
		}
		sb2 =  new StringBuilder();
		try {
		
			obj = new JSONObject(sb1.toString());
			arr = obj.getJSONArray("routes");
			legs = arr.getJSONObject(0).getJSONArray("legs");
			steps = legs.getJSONObject(0).getJSONArray("steps");
			for (i = 0; i < steps.length(); i++) {
				distance = steps.getJSONObject(i).getJSONObject("distance").getInt("value");
				if(distance > 100) {
					double x1 = steps.getJSONObject(i).getJSONObject("start_location").getDouble("lat");
					double y1 = steps.getJSONObject(i).getJSONObject("start_location").getDouble("lng");
					double x2 = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat");
					double y2 = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng");
					Point p1 = new Point(x1,y1);
					Point p2 = new Point(x2,y2);
					sb2.append(haversine(p1,p2,distance/100).toString());
				}
				else {
					double x = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat");
					double y = steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng");
					Point p = new Point(x,y);
					sb2.append(p.toString());
				}
			}
		} catch (JSONException e) {}
		write_to_file(filename2, sb2.toString());
	}
	
	// Load coordinates from files and returns a list of them
	public List<String> coords_from_file(String filename) throws IOException {
		List<String> s = new ArrayList<>();
		Pattern p = Pattern.compile("[0-9]+.[0-9]+,-[0-9]+.[0-9]+");
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line = br.readLine();
		    if(line != null) {
		    	Matcher m = p.matcher(line);
		    	while(m.find())
		    		s.add(m.group(0));
		    }
		}
		return s;
	}
	
	// Receives a list of coordinates
	// Returns a list of URL requests for the list received
	public List<String> geocoding_url_list (List<String> list, String key) {
		List<String> url = new ArrayList<>();
		for(String coordinates : list)
			url.add(reverse_geocoding(coordinates, key));
		return url;
	}
	
	// Receive a string (JSON style)
	// Return the postal-code
	public String code_from_string(String body) {
		StringBuilder s = new StringBuilder();
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{3}");
		Matcher m = p.matcher(body);
		if(m.find())
			s.append(m.group(0));
		return s.toString();
	}
	
	public List<String> codes_from_file(String filename) throws IOException {
		List<String> s = new ArrayList<>();
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{3}");
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line = br.readLine();
		    String previous = "";
		    if(line != null) {
			    Matcher m = p.matcher(line);
			   	while(m.find()) {
			   		if(!previous.equals(m.group(0)))
			   			s.add(m.group(0));
			   		previous = m.group(0);
			   	}
		    }
		}
		return s;
	}
	
	
}