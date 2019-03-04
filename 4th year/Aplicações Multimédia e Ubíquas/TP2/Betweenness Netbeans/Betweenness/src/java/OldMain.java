/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JPVS
 */
/*
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
*/
public class OldMain {
    /*
	public static void main(String args[]) throws IOException {
		
		// 1Âª FASE
		final String key = "AIzaSyBzpQoPj7JzEUIK387_2kLxn7plTS06XhA";
		SaveDiretions s;
		s = new SaveDiretions(key);
		// CREATE ALL FILES (DIRECTIONS RESPONSES)
		s.directions_requests(); 
		
		// 2Âª FASE
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
		System.out.println(rank.printMap());
		System.out.println(rank.best_code());
		System.out.println(rank.best_number());

		BigDecimal bd = new BigDecimal(((float) rank.find_code(zipcode)*5) / 190);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
		System.out.println("Your rank is " + bd);
	}*/	
}
