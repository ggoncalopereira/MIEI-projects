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
public class GetNewsSender extends Thread{
    Map<String, InetAddress> neighbours;
    private final GetNewsMessage msgG;
    DatagramSocket nextNode;
    

    public GetNewsSender(DatagramSocket socket, GetNewsMessage msg, Map<String, InetAddress> neighbours) {
        this.nextNode = socket;
        this.msgG = msg;
        this.neighbours = neighbours;
    }
    
    public synchronized void waitForNeighbours() {
        if(neighbours.isEmpty()){
            while(neighbours.isEmpty()){
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GetNewsSender.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        }
    }
    
    @Override
    public void run(){
        int i = 0,port = 9998;
        
        waitForNeighbours();
        
        //Se existir na minha tabela de vizinhos envio diretamente para ele
        if(neighbours.containsKey(msgG.getDestination())){

            try {
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                ObjectOutput oo = new ObjectOutputStream(bStream);
                oo.writeObject(msgG);
                oo.flush();
                
                byte[] serializedMessage = bStream.toByteArray();
                
                DatagramPacket sendPacket;
                sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, neighbours.get(msgG.getDestination()), port);
                nextNode.send(sendPacket);
                //System.out.println("Enviado pedido de notícias para o host: " + msgG.getDestination() +" com IP: " + neighbours.get(msgG.getDestination()));
            }catch (ConnectException e) {
                //System.out.println(e);
            } 
            catch (IOException e){
                //System.out.println(e);
            }
        }else{//senão envio para todos os meus vizinhos
            //System.out.println("Vou reencaminhar para os meus vizinhos");
            for (Map.Entry<String ,InetAddress> outerMapEntry : neighbours.entrySet()) {
                if(!outerMapEntry.getKey().equals(msgG.getOrigin())){//não envio de volta para o vizinho de onde recebi a mensagem
                    i++;//contador de envios, caso sejam 0, espera que hajam mais vizinhos além daquele de onde recebeu a notícia
                    try {
                        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        ObjectOutput oo = new ObjectOutputStream(bStream);
                        oo.writeObject(msgG);
                        oo.flush();
                
                        byte[] serializedMessage = bStream.toByteArray();
                
                        DatagramPacket sendPacket;
                        sendPacket = new DatagramPacket(serializedMessage, serializedMessage.length, outerMapEntry.getValue(), port);
                        nextNode.send(sendPacket);
                        //System.out.println("Enviado pedido de notícias para o host: " + outerMapEntry.getKey() +" com IP: " + outerMapEntry.getValue());}catch (ConnectException e) {
                    } catch (ConnectException e) {
                        //System.out.println(e);
                    } 
                    catch (IOException e){
                        //System.out.println(e);
                    }
                }
            }
            
            if(i==0){
                //System.out.println("Não enviei pedidos de notícia. Vou tentar enviar de novo");
                GetNewsSender persist = new GetNewsSender(nextNode, msgG, neighbours);
                persist.start();
            }
        }    
    }
}
