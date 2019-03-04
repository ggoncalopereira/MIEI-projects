/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

/**
 *
 * @author JPVS
 */
public class AdHocApp {
    static Map<String,InetAddress> tableV1;
    static Map<String, Map<String,InetAddress>> tableV2;
    static ArrayList<ArrayList<String>> routes;
    static Map<String, Map<String,InetAddress>> routingTable;
    
    public static void main(String args[]) throws UnknownHostException, SocketException, InterruptedException, IOException{
        InetAddress localhost = InetAddress.getLocalHost();
        //System.out.println("System IP Address: " + (localhost.getHostAddress()).trim());
        String localHostName = (localhost.getHostName()).trim();
        //System.out.println("System Name: " + localHostName);
        tableV1 = new HashMap<>();
        tableV2 = new HashMap<>();
        routes = new ArrayList<>();
        routingTable = new HashMap<>();
        
        System.out.println("HELLO PROTOCOL"); 
        System.out.println("Choose Hello Interval (>0 & ms)");
        Scanner sc = new Scanner(System.in);
        int helloInterval = sc.nextInt();
        
        System.out.println("Choose Dead Interval (>0 & ms)");
        sc = new Scanner(System.in);
        int deadInterval = sc.nextInt();
        
        System.out.println("ROUTE REQUEST PROTOCOL");
        System.out.println("Choose Timeout (>0 & ms)");
        sc = new Scanner(System.in);
        int timeout = sc.nextInt();
        
        System.out.println("Choose Radius (>0 & ms & <= 3)");
        sc = new Scanner(System.in);
        int radius = sc.nextInt();
        
        int threshold = deadInterval/helloInterval;
        //System.out.println("Threshold: " + threshold);
        
        if(threshold<1 || timeout<1){
            System.out.println("Invalid intervals");
        }else{
            //Começa à escuta na porta 9999
            UDPListener thread1 = new UDPListener(localHostName,tableV1,tableV2,deadInterval,routes);
            thread1.start();
            
            //Manda Hello de x em x segundos
            int id = 0;
            System.out.println("Multicasting to port 9999");
            Timer timer = new Timer();
            timer.schedule(new HelloPublisher(id,threshold,tableV1),1, helloInterval);
            
            long startTime = System.currentTimeMillis();
            long currentTime = System.currentTimeMillis();
            while(currentTime - startTime < deadInterval){
                currentTime = System.currentTimeMillis();
            }

            System.out.println(" Node | Neighbour | Next Hop IPV6");
            for (Map.Entry<String, InetAddress> table1MapEntry : tableV1.entrySet()) {
                for (Map.Entry<String, Map<String, InetAddress>> outerMapEntry : tableV2.entrySet()) {
                    if(table1MapEntry.getKey().equals(outerMapEntry.getKey())){ // chave da tabela 1 == chave da tabela 2
                        Map<String,InetAddress> aux = new HashMap<>();
                        for(Map.Entry<String, InetAddress> innerMapEntry : outerMapEntry.getValue().entrySet()){
                            if(!innerMapEntry.getKey().equals(localHostName)){ // verificar se o localhost == ao host na tabela 2 daquela entrada
                                System.out.println(" " + innerMapEntry.getKey() + "   |     " + outerMapEntry.getKey() + "    |  " + table1MapEntry.getValue());
                                aux.put(innerMapEntry.getKey(), table1MapEntry.getValue());
                            }                          
                        }
                        System.out.println(" " + outerMapEntry.getKey() + "   |     " + table1MapEntry.getKey() + "    |  " + table1MapEntry.getValue());
                        aux.put(table1MapEntry.getKey(),table1MapEntry.getValue());
                        routingTable.put(table1MapEntry.getKey(),aux);
                    }
                }
            }
            //Esperar que os outros hosts terminem
            startTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
            while(currentTime - startTime < 10000){
                currentTime = System.currentTimeMillis();
            }
            switch (radius) {
                case 1:
                    for (Map.Entry<String ,InetAddress> outerMapEntry : tableV1.entrySet()) {
                        ArrayList<String> path = new ArrayList<>();
                        path.add(localHostName);
                        path.add(outerMapEntry.getKey());
                        System.out.println(path.toString());
                        routes.add(path);
                    }   
                    break;
                case 2:
                    for (Map.Entry<String, Map<String, InetAddress>> outerMapEntry : tableV2.entrySet()) {
                        for (Map.Entry<String, InetAddress> innerMapEntry : outerMapEntry.getValue().entrySet()) {
                            if(!innerMapEntry.getKey().equals(localHostName)){
                                ArrayList<String> path = new ArrayList<>();
                                path.add(localHostName);
                                path.add(outerMapEntry.getKey());
                                path.add(innerMapEntry.getKey());
                                System.out.println(path.toString());
                                routes.add(path);
                            }
                        }
                    }   
                    break;
                case 3:
                    RouteMessage r = new RouteMessage(localHostName,timeout,radius);
                    long startTimeMsg = System.currentTimeMillis();
                    r.setStartTime(startTimeMsg);
                    RoutePublisher thread3 = new RoutePublisher(r);
                    thread3.start();  
                    
                    startTime = System.currentTimeMillis();
                    currentTime = System.currentTimeMillis();
                    System.out.println("Processing routes...");
                    //Certificar que as rotas estão construídas
                    //10 segundos devem ser suficientes
                    while(currentTime - startTime < 10000){
                        currentTime = System.currentTimeMillis();
                    }
                    break;
                case 4:
                    System.out.println("Not supported yet");
                    break;
                default:
                    break;
            }
        }
                
        //Pedido ao utilizador.
        //SEND == "passar a batata quente"
        //REQUEST == pedir notícias e escolher o host de qual se quer pedir
        System.out.println("Would you like to RECEIVE or SEND news?");
        sc = new Scanner(System.in);
        String answer = sc.nextLine();
        
        if(answer.equals("RECEIVE")){
            
            //Averiguar nodo para enviar pedido de notícias
            System.out.println("Which node would you like to get news from? (e.g. A1)");
            sc = new Scanner(System.in);
            String requestedHost = sc.nextLine();
            ArrayList<String> route = new ArrayList<String>();
            ArrayList<String> current;
            Iterator it = routes.iterator();
            
            
            //Verificar se há caminho para esse nodo
            while(it.hasNext()){
                current = (ArrayList<String>) it.next();
                if(current.contains(requestedHost) && current.contains(localHostName)){
                    route = current;
                    break;
                }
            }
            
            //
            if(route.size() < 0) System.out.println("There are currently no available paths to the requested host! Sorry!");
            else{
                //criar pedido
                TCPRequest request = new TCPRequest(route, requestedHost, tableV1);
                ServerSocket serversocket = new ServerSocket(9999);
                
                //enviar pedido
                TCPListener listener = new TCPListener(request);
                listener.start();
                
                //esperar que o pedido volte
                
                
                while(!listener.getArrived()){
                    Socket socket = serversocket.accept();
                    
                    BufferedReader inFromUDP = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    String received = null;
                    
                    while((received = inFromUDP.readLine()) != null){
                        System.out.println(received);
                    }
                } 
                
                //imprimir pedido
                TCPRequest info = listener.getPacket();
                System.out.println(Arrays.toString(info.getText()));
            }
        }
        else if(answer.equals("SEND")){
            TCPListener listener = new TCPListener();
            listener.run();
        }
    }
}
