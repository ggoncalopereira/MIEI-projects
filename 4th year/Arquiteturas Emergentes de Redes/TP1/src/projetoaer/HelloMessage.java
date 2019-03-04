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
 * @author jpedr
 */
public class HelloMessage implements Serializable{
    int id;
    String message;
    String hostname;
    Map<String, InetAddress> tableN;
    
    
    HelloMessage(int id1, String h, Map<String,InetAddress> t1) {
        id = id1;
        message = "hello";
        hostname = h;
        tableN = t1;
    }
    
    public int getId(){
        return this.id;
    }
        
    public String getMessage(){
        return this.message;
    }
    
    public String getHostName(){
        return this.hostname;
    }
        
    public Map<String, InetAddress> getNeighbours() {
        return this.tableN;
    }
    
}
