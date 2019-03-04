/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.net.*;

/**
 *
 * @author jpedr
 */
public class DTNApp {
    
    public static void main(String[] args) throws UnknownHostException{
        
        InetAddress localhost = InetAddress.getLocalHost();
        String localHostName = (localhost.getHostName()).trim();
        String requestedHost = null;
        
        //Iniciar o Client
        Client c = new Client(localHostName);
        c.start();
        
        //Iniciar Servidor
        Server s = new Server(localHostName,localhost);
        s.start();
    }   
}
