import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Worker {
		
	private List<Integer> times;
	private List<List<Integer>> timesMatrix;
	private List<String> destinations;
	
	public Worker() {
		times = new ArrayList<>();
		timesMatrix = new ArrayList<>();
		destinations = new ArrayList<>();
		destinations.add("41.5088491%2C-8.4622987%7C"); destinations.add("41.4989596%2C-8.435706%7C");
		destinations.add("41.5288988%2C-8.476934199999999%7C"); destinations.add("41.530903%2C-8.416241299999999%7C");
		destinations.add("41.5752248%2C-8.465715099999999%7C"); destinations.add("41.5497071%2C-8.4269395%7C");
		destinations.add("41.5503314%2C-8.389826200000002%7C"); destinations.add("41.6008097%2C-8.441818899999999%7C");
		destinations.add("41.6063051%2C-8.384566399999999%7C"); destinations.add("41.5697677%2C-8.366646899999999%7C");
	}
	
	// Receive origin postal-code and return URL request
	public String geo_coding(String origin, String key) {
		StringBuilder s = new StringBuilder();
		s.append("https://maps.googleapis.com/maps/api/geocode/json?");
		s.append("address=" + origin);
		s.append("&key=" + key + "\n");
		return s.toString();
	}
	
	// Receive origin coordinates and return URL request
	public String distance_matrix(String origin, String key) {
		StringBuilder s = new StringBuilder();
		s.append("https://maps.googleapis.com/maps/api/distancematrix/json?");
		s.append("units=metric&origins=" + origin);
		s.append("&destinations=");
		for(String str : destinations)
			s.append(str);
		s.append("&key=" + key + "\n");
		return s.toString();
	}
		
	// Returns coordinates from URL response
	public String coords_from_string(String body) {
		String s = "";
		int i = 0;
		Pattern p = Pattern.compile("(lat\" : |lng\" : )(-?[0-9]+.[0-9]+)(,?)");
		Matcher m = p.matcher(body);
		while(i<2) {
			if(m.find()) {
				s += m.group(2);
				if(i == 0)
	    			s += "%2C";
	    		else
	    			s += "%7C";
			}
			i++;
		}
		return s;
	}
	
	// Returns coordinates from a json file
	public String coords_from_file(String filename) throws IOException {
		String s = "";
		int i = 0;
		Pattern p = Pattern.compile("(lat\" : |lng\" : )(-?[0-9]+.[0-9]+)(,?)");
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line = br.readLine();
		    while(line != null && i<2) {
		    	Matcher m = p.matcher(line);
		    	if(m.find()) {
		    		s += m.group(2);
		    		if(i == 0)
		    			s += "%2C";
		    		else
		    			s += "%7C";
		    		i++;
		    	}
		    		line = br.readLine();
		    }
		}
		return s;
	}
	
	public float media() {
		float r = 0;
			for(Integer i : times)
				r+=i;
			r /= (times.size());
		return r;
	}
	
	public List<Float> mediaTotal() {
		List<Float> avg = new ArrayList<>();
		float r = 0;
		for(int i=0; i < timesMatrix.size();i++) {
			for(int j = 0;j<timesMatrix.get(i).size();j++) {
				r = timesMatrix.get(i).get(j);
			}
			r /= (timesMatrix.get(i).size());
			avg.add(r);
			r = 0;
		}

		return avg;
	}
	
	// Reads times from multiple origins from URL response
	public void elements_from_string(String body) {
		List<Integer> timesL = new ArrayList<>();
		
		Pattern p = Pattern.compile("elements");
		Matcher m = p.matcher(body);
		while(m.find()) {
			Pattern p2 = Pattern.compile("(\\d+)( min[s]?)");
			Matcher m2 = p2.matcher(body);
			while(m2.find()) {
				timesL.add(Integer.parseInt(m2.group(1)));
			}
			timesMatrix.add(timesL);
			timesL = new ArrayList<>();
		}
	}
	
	// Reads times from URL response
	public void times_from_string(String body) {
		
		Pattern p = Pattern.compile("(\\d+)( min[s]?)");
		Matcher m = p.matcher(body);
		while(m.find())
			times.add(Integer.parseInt(m.group(1)));
	}
	
	// Reads times from json file and fills times array
	public void times_from_file(String filename) throws IOException {
		
		Pattern p = Pattern.compile("(\\d+)( min[s]?)");
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line = br.readLine();
		    while (line != null) {
		    	Matcher m = p.matcher(line);
		    	if(m.find())
		    		times.add(Integer.parseInt(m.group(1)));
		    		line = br.readLine();
		    }
		}
	}
	
	
	// Returns times array
	public List<Integer> getTimes() {
		List<Integer> aux = new ArrayList<>();
		
		for(int i=0; i < timesMatrix.size();i++) {
			for(int j = 0;j<timesMatrix.get(i).size();j++) {
				Integer x = timesMatrix.get(i).get(j);
				aux.add(x);
			}
		}
		return aux;
	}
	
	public List<Integer> getTimesSize(){
		List<Integer> aux = new ArrayList<>();
		
		for(int i=0; i < timesMatrix.size();i++) {
			aux.add(timesMatrix.get(i).size());
		}
		return aux;
	}

	public Integer getTimesMatrixSize() {
		return timesMatrix.size();
	}
	

	
}
