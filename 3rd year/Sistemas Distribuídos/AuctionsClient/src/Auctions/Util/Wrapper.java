/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auctions.Util;

/**
 *
 * @author Andre
 */
public class Wrapper<T> 
{
    private T object;
    
    public Wrapper() 
    {
        object=null;
    }
    
    public Wrapper(T object)
    {
        this.object=object;
    }
        
    public synchronized boolean isNull() 
    {
        return object==null;
    }
    
    public synchronized void set(T object) 
    {
        this.object=object;
        notify();
    }
    
    public synchronized T get() 
    {
        while (isNull()) 
        { 
            try 
            {
                wait();
            } 
            catch (InterruptedException ex) 
            {}
        }
        T result=object;
        object=null;
        return result;
    }
}
