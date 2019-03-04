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

public class UDPListener extends Thread{
    Map<String,InetAddress> tableV1;
    Map<String, Map<String,InetAddress>> tableV2;
    ArrayList<ArrayList<String>> routes;
    String myName;
    int deadInterval;
    
    public UDPListener(String name, Map<String, InetAddress> t1, Map<String, Map<String,InetAddress>> t2, int d, ArrayList<ArrayList<String>> r) {
        tableV1 = t1;
        tableV2 = t2;
        myName = name;
        deadInterval = d;
        routes = r;
    }
    
    private static String removePercentageIPV6(String str) {
        String s[] = str.split("%");
        s[0] = s[0].substring(1);
        return s[0];
    } 
    
    public String getMyIP(){
        for (Map.Entry<String, Map<String, InetAddress>> outerMapEntry : tableV2.entrySet()) {
            for (Map.Entry<String, InetAddress> innerMapEntry : outerMapEntry.getValue().entrySet()) {
                if(innerMapEntry.getKey().equals(myName)){
                    return innerMapEntry.getValue().toString().trim();
                }
            }
        }
        return "";
    }
        
    /**
     *
     */
    @Override
    public void run(){
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        long startTime, currentTime;
        int port;
        
        try (MulticastSocket socket = new MulticastSocket(9999)) {
            String multicastAddress = "FF02::1";
            Inet6Address group = (Inet6Address) Inet6Address.getByName(multicastAddress);
            socket.joinGroup(group);
            
            System.out.println("Listening on port 9999");
             
            startTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
            
            while(true){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
                Object object = iStream.readObject();
                if(object instanceof HelloMessage){
                    if(currentTime - startTime < deadInterval){
                        HelloMessage msg = (HelloMessage) object;
                        String s = msg.getMessage();
                    
                        InetAddress IPAddress = receivePacket.getAddress(); 
                        String hostname = msg.getHostName();
                        if(hostname.equals(myName)){
                            //System.out.print("Receiver: RECEIVED: " + s + " WITH ID: " + msg.getId());
                            //System.out.println(" FROM Myself");
                        } else {
                            //System.out.print("RECEIVED: " + s + " WITH ID: " + msg.getId());
                            if(tableV1.containsKey(hostname)){
                                Map<String, InetAddress> aux = msg.getNeighbours();
                                tableV2.put(hostname, aux);
                                //System.out.println(": Updated neighbours table");
                            }else{
                                tableV1.put(hostname, IPAddress);
                                //System.out.println(" FROM: "+IPAddress+" HOSTNAME: "+hostname);
                                //System.out.println("Receiver: Added to neighbours list");
                            }                      
                        }
                    }
                    /*else{
                        System.out.println("Unreachable");
                    }*/
                }else if(object instanceof RouteMessage){
                    RouteMessage msg = (RouteMessage) object;
                    //System.out.println("My IP: " + removePercentageIPV6(getMyIP()));
                    String originHost = msg.getOriginHost();
                    InetAddress IPAddress;
                    
                    long startTimeMsg = msg.getStartTime();
                    int timeout = msg.getTimeout();
                    int type = msg.getType();
                    // verificar o timeout
                    if(currentTime - startTimeMsg < timeout){
                        //se a mensagem não vier de mim
                        if(!originHost.equals(myName) && type == 0){
                            for (Map.Entry<String, Map<String, InetAddress>> outerMapEntry : tableV2.entrySet()) {
                                for (Map.Entry<String, InetAddress> innerMapEntry : outerMapEntry.getValue().entrySet()) {
                                    if(!innerMapEntry.getKey().equals(myName)){
                                        ArrayList<String> path = new ArrayList<>();
                                        path.add(originHost);
                                        path.add(myName);
                                        path.add(outerMapEntry.getKey());
                                        path.add(innerMapEntry.getKey());
                                        //System.out.println("Added to route list " + path.toString());
                                        msg.addPath2Routes(path);
                                    }   
                                }
                            }
                            msg.setType(1);
                            msg.setFinalhost(myName);
                            //System.out.println("RECEIVED ROUTE_REQUEST");
                            IPAddress = receivePacket.getAddress();
                            port = receivePacket.getPort();
                            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                            ObjectOutput oo = new ObjectOutputStream(bStream);
                            oo.writeObject(msg);
                            oo.close();
                            sendData = bStream.toByteArray();                           
                            
                            //System.out.println("SENT ROUTE_REPLY");
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                            socket.send(sendPacket);
                        }else if(originHost.equals(myName) && type == 1){
                            //System.out.println("RECEIVED ROUTE_REPLY");
                            ArrayList<ArrayList<String>> pathMsg = msg.getNeighbours(); 
                            for(ArrayList<String> l : pathMsg){
                                System.out.println(l.toString());
                                routes.add(l);
                            }
                        }
                    }else{
                        System.out.println("Discard Message");
                    }
                }    
                currentTime = System.currentTimeMillis();    
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UDPListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
