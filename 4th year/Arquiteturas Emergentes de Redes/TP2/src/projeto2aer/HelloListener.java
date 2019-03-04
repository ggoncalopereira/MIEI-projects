/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPVS
 */
public class HelloListener extends Thread{
    
    Map<String, InetAddress> neighbours;
    Map<String, NeighbourManager> cache;

    String myName;
    
    public HelloListener(Map<String, InetAddress> n, String localHostName) {
        this.neighbours = n;
        this.myName = localHostName;
        this.cache = new ConcurrentHashMap<>();
    }
    
    @Override
    public void run(){
        byte[] receiveData = new byte[1024];        
       
        try {
            MulticastSocket socket = new MulticastSocket(9999);
            String multicastAddress = "FF02::1";
            Inet6Address group = (Inet6Address) Inet6Address.getByName(multicastAddress);
            socket.joinGroup(group);
            
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
                Object object = iStream.readObject();
                //Recebi um pedido de hello
                if(object instanceof HelloMessage){
                    HelloMessage msg = (HelloMessage) object;
                    InetAddress IPAddress = receivePacket.getAddress();
                    
                    if(msg.getType()==0 && !msg.getOriginHost().equals(myName)){
                        //Respondo à origem sem acrescentar à minha tabela de vizinhos porque não tenho a certeza se existe
                        //System.out.println("Recebi " + msg.getMessage() + " do host " + msg.getOriginHost() + " e vou responder");
                        msg.setDestHost(myName);
                        msg.setReply();
                        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        ObjectOutput oo = new ObjectOutputStream(bStream);
                        oo.writeObject(msg);
                        oo.flush();
                        
                        byte[] serializedMessage = bStream.toByteArray();
                        DatagramPacket sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, IPAddress, 9999);
                        socket.send(sendPacket);
          
                    }
                    else if(msg.getType()==1 && msg.getOriginHost().equals(myName)){
                        //Senão recebi um hello de outro host vou adicionar na minha tabela
                        //System.out.println("Recebi " + msg.getMessage() + " do host " + msg.getDestHost());
                        
                        String fromHost = msg.getDestHost();
                        if(neighbours.containsKey(fromHost) && cache.containsKey(fromHost)){//se já existir 
                            NeighbourManager thread = cache.get(fromHost);
                            if(thread.isAlive() && thread.getRunning()){
                                thread.interrupt();
                                thread.stopRunning();
                                cache.remove(fromHost,thread);
                                NeighbourManager newThread = new NeighbourManager(neighbours,cache,fromHost,IPAddress);
                                cache.put(fromHost, newThread);
                                newThread.start();
                            }else{
                                cache.remove(fromHost,thread);
                                NeighbourManager newThread = new NeighbourManager(neighbours,cache,fromHost,IPAddress);
                                cache.put(fromHost, newThread);
                                newThread.start();
                            }
                        }
                        else{//senão coloco em cache e na minha tabela de vizinhos
                            neighbours.put(fromHost,IPAddress);
                            //E começo um contador novo e acrescento à lista
                            NeighbourManager newThread = new NeighbourManager(neighbours,cache,fromHost,IPAddress);
                            cache.put(fromHost, newThread);
                            System.out.println("S: New host: "+fromHost);
                            newThread.start();
                        }    
                    }  
                }
            }    
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(NeighbourManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
