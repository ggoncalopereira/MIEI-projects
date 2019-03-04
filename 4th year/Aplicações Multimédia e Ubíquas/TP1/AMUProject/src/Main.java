import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;

public class Main {

	public static void main(String args[]) throws IOException {
		
		final String key_geocoding = "AIzaSyAofmUIuSdzkfFSnDYAhzWJ-0jBG_xANRk";
		final String key_distancematrix = "AIzaSyBrfXsRXKCF5-BL8h4R1NXxi-UdNvcyhYE";
		final String key_distancematrix_2 = "AIzaSyDJBg-EZ4zxtlPz-tOIgxExTn86xMa9y3Y";
		
		Worker w;
		RequestHandler request;
		String geocoding_request;
        String geocoding_response;
        String coordinates;
        String distance_request;
        String distance_response;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("1. Individual code analysis");
		System.out.println("2. General code analysis");
		System.out.println("3. Multiple Origins Request");
		System.out.println("0. Exit");
		int choice = s.nextInt();
		
		switch(choice) {
			case 1: 
				s = new Scanner(System.in);
				
				System.out.println("Choose zip code: ");
				String origin = s.nextLine();
				s.close();
				
				w = new Worker();
				request = new RequestHandler();
				
				// Obter coordenadas a partir do c贸digo postal
				geocoding_request = w.geo_coding(origin, key_geocoding);
		        geocoding_response = request.run(geocoding_request);
		        coordinates = w.coords_from_string(geocoding_response);
		        
		        System.out.println(coordinates);
		        // Guardar tempos da origem aos destinos no array times
		        distance_request = w.distance_matrix(coordinates, key_distancematrix);
		        distance_response = request.run(distance_request);
		        w.times_from_string(distance_response);
				//System.out.println("---- Link DM ----\n\t" + w.distance_matrix(coordinates, key_distancematrix));
				
				System.out.println("---- Times list ----\n\t" + w.getTimes().toString());
				
				System.out.println("---- Average ----\n\t" + w.media());
				
				// C贸digo para ler atrav茅s de ficheiros
				
				//final String json_geo = "postal.txt";
				//final String json_dist = "tempos.txt";
				
				// URL GEOCODING
				//System.out.println("---- Link GC ----\n\t" + w.geo_coding(origin, key_geocoding));
						
				// COORDINATES FROM GEOCODING JSON FILE
				//String coords = w.coords_from_file(json_geo);
				
				// URL DISTANCE MATRIX
				// TIMES FROM DISTANCE MATRIX JSON FILE
				//w.times_from_file(json_dist);
				break;
				
			case 2: 
				//Inicializa莽茫o
				TreeMap<String, Float> indexedMap = new TreeMap<>();
				s = new Scanner(System.in);
				
				//Averiguar c贸digo do input
				System.out.println("Choose zip code: ");
				String code = s.nextLine();
				s.close();
				
				//Guardar os c贸digos todos de Braga para escolher aleatoriamente
				Scanner f = new Scanner(new File("codigos.txt"));
				ArrayList<String> list = new ArrayList<String>();
				while (f.hasNext()){
				    list.add(f.next());
				}
				f.close();
				
				//Remover o c贸digo do input caso exista
				if(list.contains(code)) list.remove(code);
				
				int i; //ciclo
				double element; //elemento que vai ser convertido em 铆ndice
				
				//Guardar as m茅dias dos 10 escolhidos
				for(i = 0; i < 10; i++) {
					Random r = new Random(); //elemento random da lista dos c贸digos todos
					w = new Worker();
					request = new RequestHandler();
				
					// Obter coordenadas a partir do c贸digo postal
					geocoding_request = w.geo_coding(code, key_geocoding);
					geocoding_response = request.run(geocoding_request);
					coordinates = w.coords_from_string(geocoding_response);
		        
					// Guardar tempos da origem aos destinos no array times
					distance_request = w.distance_matrix(coordinates, key_distancematrix);
					distance_response = request.run(distance_request);
					w.times_from_string(distance_response);
					//System.out.println("---- Link DM ----\n\t" + w.distance_matrix(coordinates, key_distancematrix));
				
					//System.out.println("---- Times list ----\n\t" + w.getTimes().toString());
				
					indexedMap.put(code, w.media()); //guardar elemento num map
					
					code = list.get(r.nextInt(list.size())); //escolher novo elemento
					list.remove(code); //remover elemento da lista dos c贸digos, para n茫o haver duplicados
				}
				
				for (Map.Entry<String, Float> entry : indexedMap.entrySet()){
					if(entry.getValue().isNaN()); //do nothing
					else{
						element = (Math.abs(-(entry.getValue())+20))/4*100.0/100.0; //铆ndice de 0 a 5
						BigDecimal bd = new BigDecimal(element); //converter para decimal arrendond谩vel
					    bd = bd.setScale(2, RoundingMode.HALF_UP);
						System.out.println("Zip code: " + entry.getKey() + ", with a centrality index of " + bd);
					}
				}
				break;
			case(3):
				//para fazer request com 10 c骴igos postais precisam de estar em formato 4700-001%7C4700-002%7C
				Scanner f2 = new Scanner(new File("codigos.txt"));
				ArrayList<String> list1 = new ArrayList<String>();
				StringBuilder sb = new StringBuilder();
				int count = 0, countAux = 0;
				while (f2.hasNext() && count<2494){
					if(countAux == 10) {
						countAux = 0;
						list1.add(sb.toString());
						sb = new StringBuilder();
					}
					//adicionar os 鷏timos c骴igos postais
					if(count==2493) {
						list1.add(sb.toString());
					}
					sb.append(f2.next());
					sb.append("%7C");
					countAux++;
					count++;
				}
				f2.close();
				w = new Worker();
				request = new RequestHandler();

				String aux = list1.get(0);
				distance_request = w.distance_matrix(aux, key_distancematrix_2);
				distance_response = request.run(distance_request);
				w.elements_from_string(distance_response);
				//System.out.println(w.getTimesMatrixSize());
				
				System.out.println("---- Times list ----\n\t" + w.getTimesSize().toString());
				
				System.out.println("---- Average ----\n\t" + w.mediaTotal().toString());
			
				/*
				//250 pedidos !!!!
				for(String aux : list1) {					
					distance_request = w.distance_matrix(aux, key_distancematrix_2);
					distance_response = request.run(distance_request);
					w.elements_from_string(distance_response);
					System.out.println("---- Times list ----\n\t" + w.getTimesSize().toString());
				
					System.out.println("---- Average ----\n\t" + w.mediaTotal().toString());
				}*/
				
				break;
			default: 
				System.out.println("Bye!");
				break;
		}
		
	
	}


}