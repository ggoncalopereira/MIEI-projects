/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPVS
 */
public class RoutePublisher extends Thread{
    RouteMessage routeMessage;
    
    public RoutePublisher(RouteMessage r) {
        routeMessage = r;
    }
    
    /**
     *
     */
    @Override
    public void run(){
        //System.out.println("SENT route request WITH origin: "+ routeMessage.getOriginHost());
        try (MulticastSocket clientSocket = new MulticastSocket(9999)) {
            Inet6Address IPAddress = (Inet6Address) Inet6Address.getByName("FF02::1");
            clientSocket.setBroadcast(true);
                        
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(bStream);
            oo.writeObject(routeMessage);
            oo.close();
            byte[] serializedMessage = bStream.toByteArray();
            
            DatagramPacket sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, 9999);
            clientSocket.send(sendPacket);
            
        } catch (IOException ex) {
            Logger.getLogger(RoutePublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
