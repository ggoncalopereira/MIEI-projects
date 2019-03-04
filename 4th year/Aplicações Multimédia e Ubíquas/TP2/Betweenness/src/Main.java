import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws IOException {
		
		// 1ª FASE
		/*final String key = "AIzaSyBzpQoPj7JzEUIK387_2kLxn7plTS06XhA";
		SaveDiretions s;
		s = new SaveDiretions(key);
		// CREATE ALL FILES (DIRECTIONS RESPONSES)
		s.directions_requests(); */
		
		// 2ª FASE
		//final String key = "AIzaSyBINtawdX8ROHPCpbOiFRnpRxh610yFcPE";
		//final String key = "AIzaSyBhvvqiLfH0xxxUBRA146_4wDQCAkp_VGw";
		//final String key = "AIzaSyB1Eye-3RIvTIyilCl3nT-Kn9ylf_cu360";
		//final String key = "AIzaSyCZHuFdjRCbXhomceJFU0YkX81tq0xEmJw";
		//final String key = "AIzaSyAhuK_4_dGksK8ypMf5N0PMVE_H9C9ESpI";
		//final String key = "AIzaSyCj2C-Vp5CN7FTxADmPL0sX62oRgczOi1o";
		//final String key = "AIzaSyByl2tO55rnQZXxZdEdIYA7CU4L6AvuF_0";
		final String key = "AIzaSyCT5AiZBoM_48k_XCNdKwoj54kz99d6XZk";
		Ranking rank;
		rank = new Ranking(key);
		// CREATE ALL FILES - COORDINATES - FROM DIRECTIONS RESPONSES
		//rank.save_all_coordinates();
		
		// CREATE ALL FILES - POSTAL-CODES - FROM COORDINATES AND GEOCODING
		//rank.geocoding_requests();
		
		System.out.println("Write Zip Code");
		Scanner in = new Scanner(System.in);
		String zipcode = in.nextLine();
		// BUILD DICTIONARY - FROM POSTAL-CODES FILES
		rank.build_dictionary();
		//System.out.println(rank.printMap());
		Map<String,Integer> aux = rank.order_Map();
		//System.out.println(aux.toString());
		//System.out.println(rank.best_code());
		//System.out.println(rank.best_number());

		BigDecimal bd = new BigDecimal(((float) rank.find_code(zipcode)*5) / 190);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("Your index is " + bd);
		
		int counter = 0;
		BigDecimal comparing = new BigDecimal(counter);
		Iterator it = aux.entrySet().iterator();
		if(comparing.compareTo(bd) != 0) {
			while(it.hasNext()) {
				counter++;
				Map.Entry element = (Map.Entry) it.next();
				if(element.getKey().equals(zipcode)) {
					break;
				}
			}
			System.out.println("You are in position: " + counter + " out of " + aux.size());
		}
		else System.out.println("There are no paths available going through your zip-code. Sorry!");
	}	
}