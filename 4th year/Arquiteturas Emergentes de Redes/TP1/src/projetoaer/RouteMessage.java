/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.*;

/**
 *
 * @author JPVS
 */
public class RouteMessage implements Serializable{
    String originHost;
    int timeout; 
    int ttl;//radius
    int type;//0 - request; 1 - reply
    long startTime;
    
    String finalHost;
    
    ArrayList<ArrayList<String>> routes;
    
    public RouteMessage(String h, int time, int radius){
        originHost = h;
        ttl = radius;
        type = 0; // quando é criada uma mensagem ela vai ser request depois no reply vai-se alterar este valor
        timeout = time;
        routes = new ArrayList<>();
    }
        
    public String getOriginHost() {
        return this.originHost;
    }
    
    public int getTTL(){
        return this.ttl;
    }
    
    public int getType(){
        return this.type;
    }
    
    public long getStartTime(){
        return this.startTime;
    }
    
    public int getTimeout() {
        return this.timeout;
    }

    public String getFinalHost() {
        return this.finalHost;
    }
        
    public void decTTL(){
        this.ttl--;
    }
    
    public ArrayList<ArrayList<String>> getNeighbours() {
        return this.routes;
    }
    
    public void setType(int t){
        this.type = t;
    }
    
    public void setFinalhost(String h){
        this.finalHost = h;
    }
    
    public void setStartTime(long time){
        this.startTime = time;
    }
    
    public void setNeighbours(ArrayList<ArrayList<String>> n){
        this.routes = n;
    }

    public void addPath2Routes(ArrayList<String> path) {
        this.routes.add(path);
    }
}
