/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.Serializable;
/**
 *
 * @author jpedr
 */
public class HelloMessage implements Serializable{
    int id, type;
    String message, originHost, destHost;
    
    HelloMessage(int id1, String h) {
        id = id1;
        type = 0;
        message = "hello";
        originHost = h;
    }
    
    public int getId(){
        return this.id;
    }
        
    public String getMessage(){
        return this.message;
    }
    
    public String getOriginHost(){
        return this.originHost;
    }
    
    public String getDestHost(){
        return this.destHost;
    }
    
    public int getType(){
        return this.type;
    }
    
    public void setDestHost(String d){
        this.destHost = d;
    }
       
    public void setReply(){
        this.type = 1;
    }
    
}
