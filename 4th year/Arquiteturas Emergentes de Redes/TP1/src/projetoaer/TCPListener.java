/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoaer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonçalo
 */
public class TCPListener extends Thread{
    
    TCPRequest packet;
    DatagramSocket socket;
    boolean arrived;
    
    public TCPListener(){
        this.arrived = false;
    }
    
    public TCPListener(TCPRequest packet){
        this.packet = packet;
        this.arrived = false;
    }
    
    public TCPRequest getPacket(){
        return this.packet;
    }
    
    public boolean getArrived(){
        return this.arrived;
    }
    
    public void setArrived(){
        this.arrived = true;
    }
    
    /* idk if this will be used
    public boolean getListen(){
        return this.listen;
    }
    
    public void setListen(boolean listen){
        this.listen = listen;
    }
    */
    
    @Override
    public void run(){
        //if(this.getListen()){ forget this? idk
        while(true){
            try {
                //Inicializar valores
                byte[] receiveData = new byte[4096];
                byte[] sendData = new byte[4096];
                socket = new DatagramSocket();
                TCPListener current = this;

                //Retirar o nome do localhost
                InetAddress localhost = InetAddress.getLocalHost();
                String localHostName = (localhost.getHostName()).trim();

                //Só recebe dados do host anterior se não estiver no primeiro passo
                //(onde não recebe dados de ninguém pois são declarados de inicio)
                if(packet == null){
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);

                    ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
                    Object object = iStream.readObject();

                    current = (TCPListener) iStream.readObject();

                }

                //Extrair o TCPRequest do TCPListener
                //Se estiver no primeiro passo (pos == 0 e pedido a sair), corresponde aos dados declarados
                TCPRequest request = current.getPacket();


                    //é um pedido? se sim, entra aqui e verifica se está na altura de voltar para trás
                    if(request != null){
                        if(request.getRequest()){
                            //se estiver na altura de voltar atrás, extrai os conteudos do ficheiro
                            //e guarda-os na variável data para o reencaminhar de volta
                            if(request.getDestination().equals(localHostName)){
                                //extrai info para a variável data
                                String path = "news today!"; //a título de exemplo? precisa que se crie um news.txt na diretoria atual
                                byte[] data = path.getBytes();
                                request.setText(data);

                                //decrementa posição para saltar para trás e diz que já não é um pedido
                                request.decrementPosition();
                                request.setRequest(false);

                                //envia de volta para trás
                                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                ObjectOutput oo = new ObjectOutputStream(bStream);
                                oo.writeObject(request);
                                oo.close();

                                bStream.close();
                                //envia para o próximo por UDP
                                sendData = bStream.toByteArray();
                                String nextHost = request.getPaths().get(request.getPosition());
                                //System.out.println("next host 1 is: " + nextHost); //mete isto como comentário
                                InetAddress nextHop = request.getTable().get(nextHost);
                                // System.out.println("next hop 1 is: " + nextHop.toString()); //mete isto como comentário
                                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, nextHop, 9999);
                                socket.send(sendPacket);
                                
                            }
                            //se não estiver na altura de voltar para trás
                            //então ainda não chegou ao destino (que tem as notícias)
                            //continua a saltar para o próximo destino
                            else{
                                //incrementa posição para saber próximo salto
                                request.incrementPosition();

                                //extrai informação da stream de dados
                                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                ObjectOutput oo = new ObjectOutputStream(bStream);
                                oo.writeObject(request);
                                oo.close();
                                bStream.close();
                                
                                //envia para o próximo por UDP
                                sendData = bStream.toByteArray();
                                String nextHost = request.getPaths().get(request.getPosition());
                                //System.out.println("next host 2 is: " + nextHost); //mete isto como comentário
                                InetAddress nextHop = request.getTable().get(nextHost);
                                //System.out.println("next hop 2 is: " + nextHop.toString()); //mete isto como comentário
                                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, nextHop, 9999);
                                socket.send(sendPacket);
                            }

                        }
                        //se não é um pedido, então está a ir para trás
                        //e continua com o caminho
                        else{
                            //se a posição atual é 0, chegou ao destino
                            if(request.getPosition() == 0){
                                //assinalar que chegou ao destino
                                this.setArrived();

                                //averiguar endereço do primeiro salto para enviar por TCP
                                String firstStep = request.getPaths().get(0);
                                InetAddress firstAddress = request.getTable().get(firstStep);

                                //abrir socket para TCP e stream de dados
                                Socket clientSocket = new Socket(firstAddress, 9999);
                                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                                //escrever para socket e fechá-lo
                                outToServer.writeBytes(Arrays.toString(request.getText()));
                                clientSocket.close();
                            }
                            //se não for 0, então ainda tem de percorrer mais caminho
                            else{
                                //decrementa posição para saber o próximo salto
                                request.decrementPosition();

                                //extrai informação da stream de dados
                                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                ObjectOutput oo = new ObjectOutputStream(bStream);
                                oo.writeObject(request);
                                oo.close();
                                
                                bStream.close();
                                //converte bytes para stream compactada
                                sendData = bStream.toByteArray();

                                //averigua o próximo passo
                                String nextHost = request.getPaths().get(request.getPosition());
                                //System.out.println("next host 3 is: " + nextHost); //mete isto como comentário 
                                InetAddress nextHop = request.getTable().get(nextHost);
                                //System.out.println("next hop 3 is: " + nextHop.toString()); //mete isto como comentário
                                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, nextHop, 9999);
                                socket.send(sendPacket);
                            }
                        }
                    }
                    else{
                        System.out.println("Waiting for packet...");
                        Thread.sleep(3000);
                    }
                }
            catch (IOException | ClassNotFoundException | InterruptedException ex) {
                Logger.getLogger(TCPListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
