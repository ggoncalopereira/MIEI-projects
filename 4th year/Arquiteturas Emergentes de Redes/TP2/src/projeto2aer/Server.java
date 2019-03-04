/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpedr
 */
public class Server extends Thread{
    String myName;
    InetAddress localhost;
    Map<String,InetAddress> neighbours;
    
    public Server(String localHostName, InetAddress localhost) {
        this.myName = localHostName;
        this.localhost = localhost;
    }
    
    @Override
    public void run(){
        neighbours = new ConcurrentHashMap<>();
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket;
        
        try {
            welcomeSocket = new ServerSocket(9999);
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                //System.out.println("S: Received: " + clientSentence);
                //Iniciar as threads
                
                //Open Thread to update available neighbours receiving and answering to hello messages
                System.out.println("S: Listening on UDP port 9999");
                HelloListener thread1 = new HelloListener(neighbours,myName);
                thread1.start();
        
                //Open HelloTransmitter to send HELLO FROM HOST
                System.out.println("S: Multicasting hello messages to UDP port 9999");
                HelloTransmitter thread2 = new HelloTransmitter(myName);
                thread2.start();
        
                //Open News HelloListener
                System.out.println("S: Answering to News Requests");
                NewsListener thread3 = new NewsListener(connectionSocket,clientSentence,neighbours,myName);
                thread3.start();

            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

  
 
    }
}
