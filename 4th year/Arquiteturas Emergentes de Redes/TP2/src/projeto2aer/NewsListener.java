/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPVS
 */
public class NewsListener extends Thread{

    Map<String, InetAddress> neighbours;
    Map<String, GetNewsMessage> cacheGet;
    Map<String, NewsMessage> cacheNews;
    String myName,clientSentence;
    Socket clientSocket;
    volatile boolean received;
    AtomicBoolean rec;
    
    public NewsListener(Socket connectionSocket, String c, Map<String, InetAddress> n, String l) {
        this.clientSocket = connectionSocket;
        this.neighbours = n;
        this.myName = l;
        this.cacheGet = new ConcurrentHashMap<>();
        this.cacheNews = new ConcurrentHashMap<>();
        this.clientSentence = c;
        this.received = false;
        this.rec = new AtomicBoolean(false);
    }

    public synchronized void setTrue(){
        this.received = true;
    }
        
    @Override
    public void run(){
        byte[] receiveData = new byte[1024]; 
        int id = 0;
        long startTime = System.currentTimeMillis();
        long currentTime;
        
        try {
            DatagramSocket socket = new DatagramSocket(9998);
            //Open News Sender
            if(!clientSentence.equals("null")){
                System.out.println("S: Sending News Requests");
                GetNewsMessage msg = new GetNewsMessage(id,myName,clientSentence);
                GetNewsSender thread4 = new GetNewsSender(socket,msg,neighbours);//envia as mensagens de GetNews
                thread4.start();
            }
            
            if(!clientSentence.equals("null")){
                SendNewRequest threadS = new SendNewRequest(rec,socket,received,myName,clientSentence,neighbours);
                threadS.start();
            }
            
            
            while(true){
                                
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
                Object object = iStream.readObject();
                
                if(object instanceof GetNewsMessage){
                    GetNewsMessage msgG = (GetNewsMessage) object;
                    //System.out.println("Recebi um pedido de notícias com origem no host: " + msgG.getOrigin()+" com destino: " + msgG.getDestination());
                    
                    if(msgG.getDestination().equals(myName) && !msgG.getOrigin().equals(myName)){
                        //System.out.println("Sou eu o destinatário e vou responder com notícias");
                        //verificar os vizinhos disponiveis e enviar
                        //verificar se já recebi esta mensagem para não ter de responder de novo
                        if(!cacheGet.containsKey(msgG.getOrigin())){
                            //System.out.println("Não existe na cache");
                            cacheGet.put(msgG.getOrigin(), msgG);
                            NewsMessage msg2N = new NewsMessage(0,myName,msgG.originHost);
                    
                            NewsSender threadAux2 = new NewsSender(socket,msg2N,neighbours);
                            threadAux2.start(); 
                        }                        
                    }
                    else if(!msgG.getOrigin().equals(myName)){
                        //verificar se esta mensagem já passou por mim vendo a cache
                        //se não tiver este vizinho
                        if(!cacheGet.containsKey(msgG.getOrigin())){
                            cacheGet.put(msgG.getOrigin(), msgG);
                            //verifica os vizinhos disponiveis e enviar
                            GetNewsSender threadAux = new GetNewsSender(socket,msgG,neighbours);
                            threadAux.start();
                        }else{//caso contrário ponho em cache
                            GetNewsMessage aux = cacheGet.get(msgG.getOrigin());
                            if(msgG.getGenDate() > aux.getGenDate()){ //se é mais recente atualizo a entrada
                                cacheGet.put(msgG.getOrigin(), msgG);
                                System.out.println("Atualizei cache");
                                GetNewsSender threadAux = new GetNewsSender(socket,msgG,neighbours);
                                threadAux.start();
                            }
                        }                       
                    }
                    
                }else if(object instanceof NewsMessage){
                    NewsMessage msgN = (NewsMessage) object;
                    //System.out.println("Recebi notícias com origem no host: " + msgN.getOrigin()+" com destino: " + msgN.getDestination());
                    
                    if(msgN.getDestination().equals(myName) && !msgN.getOrigin().equals(myName)){
                        if(!cacheNews.containsKey(msgN.getOrigin())){ //se não tiver esta mensagem coloca em cache
                            cacheNews.put(msgN.getOrigin(), msgN);
                            System.out.println("Sou eu o destinatário");
                            //received = true;
                            setTrue();
                            this.rec.set(true);
                            //Imprimir notícias
                            String answer = "Noticias do Host: " + msgN.getOrigin() + " : " +msgN.getNews() + " Data: "+msgN.getGenDate() +'\n';
                            //System.out.println(answer);
                            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
                            outToClient.writeBytes(answer);  
                            
                        }
                        
                    }else if(!msgN.getOrigin().equals(myName)){
                        //verificar se esta mensagem já passou por mim vendo a cache
                        //caso não tenha mensagens deste host
                        if(!cacheNews.containsKey(msgN.getOrigin())){
                            cacheNews.put(msgN.getOrigin(), msgN);
                            //verificar os vizinhos disponiveis e enviar
                            NewsSender threadAux2 = new NewsSender(socket,msgN,neighbours);
                            threadAux2.start();   
                        }else{//caso contrário ponho em cache  
                            NewsMessage aux = cacheNews.get(msgN.getOrigin());
                            if(msgN.getGenDate() > aux.getGenDate()){ //se é mais recente atualizo a entrada
                                cacheNews.put(msgN.getOrigin(), msgN);
                                System.out.println("Atualizei cache");
                                NewsSender threadAux2 = new NewsSender(socket,msgN,neighbours);
                                threadAux2.start(); 
                            }
                        }
                    }
                }
            } 
        }
        catch (ConnectException e) {
            System.out.println(e);
        } 
        catch (IOException e){
            System.out.println(e);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(NewsListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
