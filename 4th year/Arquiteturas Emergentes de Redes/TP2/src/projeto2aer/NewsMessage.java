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
public class NewsMessage implements Serializable{
    int id;
    String originHost;
    String destHost;
    long genDate;
    String news;
    
    public NewsMessage(int i,String o, String d){
        this.id = i;
        this.originHost = o;
        this.destHost = d;
        this.news = "Ola do host " + originHost;
        this.genDate = System.currentTimeMillis();
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getOrigin(){
        return this.originHost;
    }
    
    public String getDestination(){
        return this.destHost;
    }
    
    public long getGenDate(){
        return this.genDate;
    }
    
    public String getNews(){
        return this.news;
    }
       
    public void setNews(String n){
        this.news = n;
    }
}
