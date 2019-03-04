/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.net.*;
import java.util.*;


/**
 *
 * @author jpedr
 */
public class NeighbourManager extends Thread{

    Map<String, InetAddress> neighbours;
    Map<String, NeighbourManager> cache;
    String fromHost;
    InetAddress ipNeighbour;
    boolean running;
        

    public NeighbourManager(Map<String, InetAddress> n, Map<String, NeighbourManager> c, String f, InetAddress IPAddress) {
        this.neighbours = n;
        this.cache = c;
        this.fromHost = f;
        this.ipNeighbour = IPAddress;
        this.running = true;
    }
    
    public void stopRunning(){
        running = false;
    }
    
    public boolean getRunning() {
        return this.running;
    }
    
    /**
     * Contador que vai contar o timeout dos hosts
     */
    @Override
    public void run(){
        int deadInterval = 3000;
        
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while(currentTime - startTime < deadInterval && !NeighbourManager.interrupted()){
            currentTime = System.currentTimeMillis();
        }
        
        if(!NeighbourManager.interrupted() && running){ //se chegar ao fim do ciclo retiro da minha lista
            System.out.println("S: Time out for host: " + fromHost);
            neighbours.remove(fromHost,ipNeighbour);
            cache.remove(fromHost,this);
        }else if (NeighbourManager.interrupted() && !running){
            System.out.println("S: Forced break: ");
        }   
    }
}
