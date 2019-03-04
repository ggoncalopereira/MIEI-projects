/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2aer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JPVS
 */
public class NewsSender extends Thread{
    Map<String, InetAddress> neighbours;
    private final NewsMessage msgN;
    DatagramSocket nextNode;
    
    public NewsSender(DatagramSocket socket, NewsMessage msg, Map<String, InetAddress> neighbours) {
        this.nextNode = socket;
        this.msgN = msg;
        this.neighbours = neighbours;
        
    }
    
    public synchronized void waitForNeighbours(){
        if(neighbours.isEmpty()){
            while(neighbours.isEmpty()){
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NewsSender.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        }
    }
    
    @Override
    public void run(){
        int i = 0, port = 9998;
        
        waitForNeighbours();
        
        //Se existir na minha tabela de vizinhos envio diretamente para ele
        if(neighbours.containsKey(msgN.getDestination())){

            try {
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                ObjectOutput oo = new ObjectOutputStream(bStream);
                oo.writeObject(msgN);
                oo.flush();
                
                byte[] serializedMessage = bStream.toByteArray();
                
                DatagramPacket sendPacket;
                sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, neighbours.get(msgN.getDestination()), port);
                nextNode.send(sendPacket);
                //System.out.println("Enviado notícias para o host: " + msgN.getDestination() +" com IP: " + neighbours.get(msgN.getDestination()));
            }catch (ConnectException e) {
                //System.out.println(e);
            } 
            catch (IOException e){
                //System.out.println(e);
            }
        }
        else{//senão envio para todos os meus vizinhos
            //System.out.println("Vou reencaminhar para os meus vizinhos");
            for (Map.Entry<String ,InetAddress> outerMapEntry : neighbours.entrySet()) {
                if(!outerMapEntry.getKey().equals(msgN.getOrigin())){ //não envio de volta para o vizinho de onde recebi a mensagem
                    i++;//contador de envios, caso sejam 0, espera que hajam mais vizinhos além daquele de onde recebeu a notícia
                    try {
                        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        ObjectOutput oo = new ObjectOutputStream(bStream);
                        oo.writeObject(msgN);
                        oo.flush();
                
                        byte[] serializedMessage = bStream.toByteArray();
                
                        DatagramPacket sendPacket;
                        sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, outerMapEntry.getValue(), port);
                        nextNode.send(sendPacket);
                        //System.out.println("Enviado notícias para o host: " + outerMapEntry.getKey() +" com IP: " + outerMapEntry.getValue());
                    }catch (ConnectException e) {
                        //System.out.println(e);
                    } 
                    catch (IOException e){
                        //System.out.println(e);
                    }
                }
            }
            
            if(i==0){
                System.out.println("Não enviei notícias. Vou tentar enviar de novo");
                NewsSender persist = new NewsSender(nextNode, msgN, neighbours);
                persist.start();
            }
        }
    }
}
