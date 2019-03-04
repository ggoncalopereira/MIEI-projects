/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Gonçalo
 */
public class TCPRequest implements Serializable{
    
    String destination;
    Map<String,InetAddress> table;
    ArrayList<String> paths;
    boolean request;
    byte[] text;
    int position;
    
    public TCPRequest(ArrayList<String> paths, String destination, Map<String,InetAddress> table){
        this.paths = paths;
        this.destination = destination;
        this.text = new byte[2048];
        this.request = true;
        this.position = 0;
        this.table = table;
    }
    
    public ArrayList<String> getPaths(){
        return this.paths;
    }
    
    public String getDestination(){
        return this.destination;
    }
    
    public boolean getRequest(){
        return this.request;
    }
    
    public byte[] getText(){
        return this.text;
    }
    
    public int getPosition(){
        return this.position;
    }
    
    public Map<String,InetAddress> getTable(){
        return this.table;
    }
    
    public void setDestination(String destination){
        this.destination = destination;
    }
    
    public void setRequest(boolean request){
        this.request = request;
    }
    
    public void setPaths(ArrayList<String> paths){
        this.paths = paths;
    }
    
    public void setText(byte[] text){
        this.text = text;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public void incrementPosition(){
        int temp = this.position;
        this.position = temp + 1;
    }
    
    public void decrementPosition(){
        int temp = this.position;
        this.position = temp - 1;
    }
    
    public void setTable(Map<String,InetAddress> table){
        this.table = table;
    }
}
