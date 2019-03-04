/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPVS
 */
public class HelloTransmitter extends Thread{

    String myName;
    
    public HelloTransmitter(String localHostName) {
        this.myName = localHostName;
    }
    
    //Envia de x em x segundos ou a partir de outro parâmetro (tipo a velocidade do nó) mensagens de getNews
    
    @Override
    public void run(){
        int id = 0;
        
        while(true){
            int port = 9999;
            String multicastAddress = "FF02::1";
            try (DatagramSocket clientSocket = new DatagramSocket()) {
                
                Inet6Address IPAddress = (Inet6Address) Inet6Address.getByName(multicastAddress);
                
                HelloMessage msg;
                msg = new HelloMessage(id,myName);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                ObjectOutput oo = new ObjectOutputStream(bStream);
                oo.writeObject(msg);
                oo.flush();
                
                byte[] serializedMessage = bStream.toByteArray();
                
                DatagramPacket sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, port);
                clientSocket.send(sendPacket);
                Thread.sleep(2000);
                id++;
            } catch (SocketException ex) {
                Logger.getLogger(HelloTransmitter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException | InterruptedException ex) {
                Logger.getLogger(HelloTransmitter.class.getName()).log(Level.SEVERE, null, ex);            
            } catch (IOException ex) {
                Logger.getLogger(HelloTransmitter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
