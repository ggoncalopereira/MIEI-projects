/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author JPVS
 */
public class SendNewRequest extends Thread{
    String clientSentence, myName;
    volatile boolean received;
    DatagramSocket socket;
    Map<String, InetAddress> neighbours;
    AtomicBoolean rec;


    public SendNewRequest(AtomicBoolean rec, DatagramSocket socket, boolean received, String l, String c, Map<String, InetAddress> n) {
        this.socket = socket;
        this.neighbours = n;
        this.myName = l;
        this.clientSentence = c;
        this.received = received;
        this.rec = rec;
    }
    
    
    @Override
    public void run(){
        
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        int timeout = 10000;
        int id = 1;
        
        while(!rec.get()){
            
            currentTime = System.currentTimeMillis();
            if(currentTime - startTime < timeout ){
                //System.out.println("received: " + received + " S: " + startTime + " C: " + currentTime);
            }else{
                startTime = System.currentTimeMillis();
                System.out.println("S: Sending new News Requests");
                GetNewsMessage msgNew = new GetNewsMessage(id,myName,clientSentence);
                GetNewsSender thread5 = new GetNewsSender(socket,msgNew,neighbours);//volta a enviar as mensagens de GetNews
                thread5.start();
                id++;  
            }
            
        }
    }
}
