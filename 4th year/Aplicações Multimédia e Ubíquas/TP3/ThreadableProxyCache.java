import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ThreadableProxyCache implements Runnable{
	
	private final Socket client;

    public ThreadableProxyCache(Socket client) {
        this.client = client;

    }	

    public void run(){
    	handle(client);
    }


    public static void handle(Socket client) {
		Socket server = null;
		HttpRequest request = null;
		HttpResponse response = null;

		/* Process request. If there are any exceptions, then simply
	 	* return and end this request. This unfortunately means the
	 	* client will hang for a while, until it timeouts. */

		/* Read request */
		try {
	   	 	BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    	request = new HttpRequest(fromClient);
		} catch (IOException e) {
	   		System.out.println("Error reading request from client: " + e);
	    	return;
		} catch (NullPointerException e){
			return;
		}
		/* Send request to server */
		try {
		    /* Open socket and write request to socket */
		    server = new Socket(request.getHost(), request.getPort());
		    DataOutputStream toServer = new DataOutputStream(server.getOutputStream()); 
	        toServer.writeBytes(request.toString());
		} catch (UnknownHostException e) {
		    System.out.println("Unknown host: " + request.getHost());
		    System.out.println(e);
		    return;
		} catch (IOException e) {
			System.out.println("Error writing request to server: " + e);
		    return;
		} catch (NullPointerException e){
			return;
		}
		/* Read response and forward it to client */
		try {
			byte[] cache = ProxyCache.findInCache(request.URI);                              
    		if(cache.length==0) {                                                             
        		DataInputStream fromServer = new DataInputStream(server.getInputStream());
        		response = new HttpResponse(fromServer); 
        		DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
				toClient.writeBytes(response.toString()); 
        		toClient.write(response.body);
        		/* Write response to client. First headers, then body */
				ProxyCache.putInCache(request, response);
				client.close();
        		server.close();
        		/* Insert object into the cache */
        		/* Fill in (optional exercise only) */
    		}
    		else{                                                                             
      			DataOutputStream toClient = new DataOutputStream(client.getOutputStream());    
      			toClient.write(cache);                                                         
      			client.close();                                                                 
      			server.close();                                                                
   			}
		} catch (IOException e) {
		    System.out.println("Error writing response to client: " + e);
		} catch (NullPointerException e){
			return;
		}
    }
}
