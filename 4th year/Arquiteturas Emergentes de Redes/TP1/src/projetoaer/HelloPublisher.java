/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloPublisher extends TimerTask{
    
    int id; 
    int threshold;
    Map<String,InetAddress> tableV1;
    boolean hasStarted;
    
    public HelloPublisher(int i, int t, Map<String,InetAddress> t1){
        id = i;
        threshold = t;
        tableV1 = t1;
        hasStarted = false;
    }

    /**
     *
     */
    @Override
    public void run(){
        hasStarted = true;
        try { 
            InetAddress localhost = InetAddress.getLocalHost();
            String localHostName = (localhost.getHostName()).trim();
            
            int port = 9999;
            String multicastAddress = "FF02::1";
                        
            //System.out.println("SENT: hello WITH ID: "+ id);
                
            try (DatagramSocket clientSocket = new DatagramSocket()) {
                    
                Inet6Address IPAddress = (Inet6Address) Inet6Address.getByName(multicastAddress);
                     
                HelloMessage msg;
                msg = new HelloMessage(id,localHostName,tableV1);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                ObjectOutput oo = new ObjectOutputStream(bStream);
                oo.writeObject(msg);
                oo.close();
                    
                byte[] serializedMessage = bStream.toByteArray();
                    
                DatagramPacket sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, port);
                clientSocket.send(sendPacket);
                    
            } catch (SocketException ex) {
                Logger.getLogger(HelloPublisher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(HelloPublisher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HelloPublisher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnknownHostException ex) {            
            Logger.getLogger(HelloPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        id++;
        if(id == threshold){
            //System.out.println("THRESHOLD");
            hasStarted = false;
            this.cancel();
        }
    }
}
