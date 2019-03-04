/**
 * ProxyCache.java - Simple caching proxy
 *
 * $Id: ProxyCache.java,v 1.3 2004/02/16 15:22:00 kangasha Exp $
 *
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class ProxyCache {
    /** Port for the proxy */
    private static int port;
    /** Socket for client connections */
    private static ServerSocket socket;

    private static Map<String, String> cache = new Hashtable<String, String>();

    public static void init(int p) {
		port = p;
		File f = new File("cache");
		try{
   			if(f.mkdir()) { 
        		System.out.println("Directory Created");
    		} else {
        		System.out.println("Directory already exists");
    		}
		} catch(Exception e){
    		e.printStackTrace();
		}
		try {
		    socket = new ServerSocket(port);
		} catch (IOException e) {
		    System.out.println("Error creating socket: " + e);
		    System.exit(-1);
		}
    }

    public synchronized static void putInCache(HttpRequest request, HttpResponse reply) throws IOException{
  		File file;
  		DataOutputStream toFile;

		file = new File("cache/cached_"+System.currentTimeMillis());
  		toFile = new DataOutputStream( new FileOutputStream(file));
  		toFile.writeBytes(reply.toString());
  		toFile.write(reply.body);
  		toFile.close();
  		cache.put(request.URI, file.getAbsolutePath());
  		System.out.println("Writing in cache the following request " + request.URI + " to file " + file.getAbsolutePath());
	}

	public synchronized static byte[] findInCache(String uri) throws IOException{
	  	File file;
	  	FileInputStream fromFile;
	  	String fileHash;
 	  	byte[] cachedBytes;

	  	if((fileHash = cache.get(uri))!=null){
		    file = new File(fileHash);
		    fromFile = new FileInputStream(file);
		    cachedBytes = new byte[(int)file.length()];
		    fromFile.read(cachedBytes);
		    System.out.println("Cache hit on " + uri);
		    return cachedBytes;
	  	}
	  	else {
		    System.out.println("Cache miss on "+uri);
		    return cachedBytes = new byte[0];
	  	}
	}

    /** Read command line arguments and start proxy */
    public static void main(String args[]) {
		int myPort = 0;
		
		try {
		    myPort = Integer.parseInt(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
		    System.out.println("Need port number as argument");
		    System.exit(-1);
		} catch (NumberFormatException e) {
		    System.out.println("Please give port number as integer.");
		    System.exit(-1);
		}
		
		init(myPort);

		/** Main loop. Listen for incoming connections and spawn a new
		 * thread for handling them */
		Socket client = null;
		
		while (true) {
		    try {
				client = socket.accept();
				(new Thread(new ThreadableProxyCache(client))).start();
		    } catch (IOException e) {
				System.out.println("Error reading request from client: " + e);
				/* Definitely cannot continue processing this request,
			 	 * so skip to next iteration of while loop. */
			continue;
		    }
		}
    }
}