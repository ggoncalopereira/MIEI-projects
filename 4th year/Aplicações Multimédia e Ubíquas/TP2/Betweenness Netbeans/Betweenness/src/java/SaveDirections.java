/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JPVS
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveDirections {

	private List<String> destinations;
	private String key;
	RequestHandler r;
	Worker w;

	public SaveDirections(String key) {
		destinations = new ArrayList<String>();
		destinations.add("4705-014"); destinations.add("4705-627"); destinations.add("4705-473");
		destinations.add("4705-545"); destinations.add("4705-480"); destinations.add("4705-314");
		destinations.add("4705-046"); destinations.add("4705-791"); destinations.add("4700-770");
		destinations.add("4715-342"); destinations.add("4705-309"); destinations.add("4700-658");
		destinations.add("4700-442"); destinations.add("4700-830"); destinations.add("4715-295");
		destinations.add("4700-020"); destinations.add("4700-081"); destinations.add("4710-586");
		destinations.add("4715-538"); destinations.add("4710-617");
		this.key = key;
		r = new RequestHandler();
		w = new Worker();
	}
	
	// Writes all Directions Responses to files
	public void directions_requests() throws IOException {
		String o, d, request, response, filename;
		int i, j;
		for(i = 0 ; i < 20; i++)
			for(j = i+1; j < 20; j++) {
				o = destinations.get(i);
				d = destinations.get(j);
				request = w.directions(o, d, key);
				response = r.run(request);
				filename = "directions" + "_" + i + "_" + j;
				w.write_to_file(filename, response);
			}
	}
	
}
