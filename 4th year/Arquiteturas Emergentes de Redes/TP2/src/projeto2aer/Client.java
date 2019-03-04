/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpedr
 */
public class Client extends Thread{
    String myName;
    
    public Client(String localHostName) {
        this.myName = localHostName;
    }
    
    @Override
    public void run(){
        String sentence, modifiedSentence,requestedHost = null;
        System.out.println("Would you like to RECEIVE (R) or SEND(S) news?");
        
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if(command.equals("R")){
            System.out.println("Which node would you like to get news from? (e.g. A1)");
            sc = new Scanner(System.in);
            requestedHost = sc.nextLine();
            if(requestedHost.equals(myName)){
                System.out.println("Choose another host");
                return;
            }
        }else if(command.equals("S")){
            requestedHost = null;
        }
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", 9999);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentence = requestedHost;
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("From Server: " + modifiedSentence);
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
