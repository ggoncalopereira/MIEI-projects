import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ranking {

	private Map<String, Integer> map;
	private String key;
	RequestHandler r;
	Worker w;
	
	public Ranking(String key) {
		this.map = new HashMap<>();
		this.key = key;
		w = new Worker();
		r = new RequestHandler();
	}
	
	public void save_all_coordinates() throws IOException {
		int i, j;
		for(i = 0 ; i < 20; i++)
			for(j = i+1; j < 20; j++) {
				String filename1 = "./Directions/directions_" + i + "_" + j; 
				String filename2 = "./Coordinates/coordinates_" + i + "_" + j;
				w.coord_from_json_to_file(filename1, filename2);
			}
	}
	
	public void geocoding_requests() throws IOException {
		int i, j;
		List<String> aux = new ArrayList<>();
		List<String> urls = new ArrayList<>();
		List<String> codes = new ArrayList<>();
		// ALTERAR PARA O PRETENDIDO
		for(i = 16 ; i < 20 ; i++)
			for(j = i+1; j < 20; j++) {
				String filename1 = "./Coordinates/coordinates_" + i + "_" + j;
				String filename2 = "./Geocoding/geocoding_" + i + "_" + j;
				aux = w.coords_from_file(filename1);
				if(aux != null) {
					urls = w.geocoding_url_list(aux, key);
					for(String url : urls) {
						String response = r.run(url);
						codes.add(w.code_from_string(response));
					}
					System.out.println("Code " + i + " to " + j + " done!");
					w.write_to_file(filename2, codes.toString());
					aux.clear(); 
					urls.clear();
					codes.clear();
				}
			}
	}
	
	public void build_dictionary() throws IOException {
		List<String> aux = new ArrayList<>();
		int i, j;
		for(i = 0 ; i < 20; i++)
			for(j = i+1; j < 20; j++) {
				String filename = "./Geocoding/geocoding_" + i + "_" + j;
				aux = w.codes_from_file(filename);
				for(String code : aux)
					map.compute(code, (key, oldValue) -> ((oldValue == null) ? 1 : oldValue+1));
			}
	}
	
	public String printMap() {
		return this.map.toString();
	}
	
	public String best_code() {
		Integer max = 1;
		String code = "Nothing";
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    if (value.intValue() > max.intValue()) {
		    	max = value;
		    	code = key;
		    }
		}
		return code;
	}
	
	public Integer best_number() {
		Integer max = 1;
		for(Integer n : map.values()) {
			if(n.intValue() > max.intValue())
				max = n;
		}
		return max;
	}
	
	public Integer find_code(String code) {
		if(this.map.containsKey(code)) {
			return this.map.get(code);
		}
		return 0;
		
	}
	
	public Integer get_Size() {
		return this.map.size();
	}
	
	public Map<String,Integer> order_Map(){
		 Map<String, Integer> result2 = new LinkedHashMap<>();
	     this.map.entrySet().stream()
	                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
		return result2;
	}
}