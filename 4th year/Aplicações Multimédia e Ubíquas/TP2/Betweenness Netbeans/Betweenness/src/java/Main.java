/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JPVS
 */
public class Main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String zipcode = request.getParameter("zipcode");
            out.println("<!DOCTYPE html>");
            out.println("<style>\n" + "body {font-family: Arial, Helvetica, sans-serif;}\n</style>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Main</title>"
                    + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                        "<style>\n" + ".checked {\n" +  "    color: orange;\n" + "}\n" + "</style>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Código-Postal Inserido "+ zipcode + "</h1>");
            // 1ª FASE
            /*final String key = "AIzaSyBzpQoPj7JzEUIK387_2kLxn7plTS06XhA";
            SaveDiretions s;
            s = new SaveDiretions(key);
            // CREATE ALL FILES (DIRECTIONS RESPONSES)
            s.directions_requests(); */
		
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
	
            // BUILD DICTIONARY - FROM POSTAL-CODES FILES
            rank.build_dictionary();
            Map<String,Integer> aux = rank.order_Map();
            //System.out.println(aux.toString());
            //System.out.println(rank.best_code());
            //System.out.println(rank.best_number());
            
            
            BigDecimal bd = new BigDecimal(((float) rank.find_code(zipcode)*5) / 190);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		
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
            //System.out.println("You are in position: " + counter + " out of " + aux.size());
            out.println("<h2>Index: "+ bd + "</h2>");
            int j = bd.intValue();
            for(int i=0 ;i < j;i++){
                out.println("<span class=\"fa fa-star checked\"></span>");
            }
            for(int i=j ; i<5 ;i++){
                out.println("<span class=\"fa fa-star\"></span>");
            }
            out.println("<h3>Position: "+ counter + " out of " + aux.size() + "</h3>");
            out.println("<ol>");
            for(Map.Entry<String,Integer> printmap : aux.entrySet()){
                String partes[] = printmap.getKey().split("-");
                    String cp4 = partes[0] ,cp3 = partes[1];
                    String url = "https://www.codigo-postal.pt/?cp4="+ cp4 +"&cp3=" + cp3;
                    out.println("<li>Código: " + printmap.getKey() + "\nTotal de Caminhos: " + printmap.getValue() + "<p> <a href=" + url + ">Localização</a></p></li>");
            }
            out.println("</ol>");
            //System.out.println(rank.printMap());
            //System.out.println(rank.best_code());
            //System.out.println(rank.best_number());
            out.println("</body>");
            out.println("</html>");
            }else{
                //System.out.println("There are no paths available going through your zip-code. Sorry!");
                out.println("There are no paths available going through your zip-code. Sorry!");
                out.println("<ol>");
                for(Map.Entry<String,Integer> printmap : aux.entrySet()){
                    
                    String partes[] = printmap.getKey().split("-");
                    String cp4 = partes[0] ,cp3 = partes[1];
                    String url = "https://www.codigo-postal.pt/?cp4="+ cp4 +"&cp3=" + cp3;
                    out.println("<li>Código: " + printmap.getKey() + "\nTotal de Caminhos: " + printmap.getValue() + " Localização: " + url + "</li>");
                }
                out.println("</ol>");
                out.println("</body>");
                out.println("</html>");
            } 
            
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
