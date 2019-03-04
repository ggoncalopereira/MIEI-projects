
package Auctions.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * AuctionReaders are in charge of handling all input from clients
 * and delegating that input to its ClientsManager or AuctionsManager,
 * according to if said input is clients-related or auctions-related.
 * Management of communication is delegated to its Managers as well.
 *
 * @author André Diogo, Gonçalo Pereira, António Silva
 *
 */
public class WorkerReader implements Runnable 
{
    private final Socket SocketToRead;
    private final ClientsManager ClientsManager;
    private final AuctionsManager AuctionsManager;
    private final BufferedReader SocketInput;
    private String User;
    private String Password;
    private final Future Processor;
    
    /**
     * 
     * @param SocketToRead The client socket this worker handles
     * @param ClientsManager The ClientsManager this worker delegates 
     * client-specific input to.
     * @param AuctionsManager The AuctionsManager this worker delegates
     * auctions-specific input to.
     * @param SocketInput A reader over a socket input stream.
     * @param Processor A processor thread's computation to interrupt on exit.
     */
    public WorkerReader(Socket SocketToRead, 
                        ClientsManager ClientsManager,
                        AuctionsManager AuctionsManager,
                        BufferedReader SocketInput,
                        Future Processor) 
    {
        this.SocketToRead=SocketToRead;
        this.ClientsManager=ClientsManager;
        this.AuctionsManager=AuctionsManager;
        this.SocketInput=SocketInput;
        this.Processor = Processor;
    }

    
    @Override
    public void run() 
    {
        System.out.println("Worker Reader Start");
        boolean successfulLogin;
        successfulLogin=attemptLogin();
        if (successfulLogin) 
        {
            attemptReads();
            ClientsManager.atSocketDisconnected(User);
        }  
        try
        {
            SocketToRead.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


    
    /**
     * A login is indicate by a 0.
     * A registration is indicated by not a 0. Implementation dependent.
     * A cycle of attempting to login. Fails if a socket dies before
     * a successful authentication.
     * A registration does not login a user.
     * @return if the login was successful.
     */
    private boolean attemptLogin() 
    {
        SimpleQueue<LoginRequest> RequestContainer = 
                ClientsManager.getSocketToProcessorRequest(SocketToRead);
        SimpleQueue<Boolean> ResponseContainer = 
                ClientsManager.getProcessorToSocketResponse(SocketToRead);
        boolean SuccessfulLogin=false;
        try 
        { 
            while (!SuccessfulLogin)
            {
                boolean IsLogin = SocketInput.readLine().equals("0");
                User=SocketInput.readLine();
                Password = null;
                if (User!=null)
                    Password=SocketInput.readLine();
                if (Password!=null) 
                {    
                    LoginRequest Request = new LoginRequest(IsLogin,User,
                                                      Password,SocketToRead);
                    if (IsLogin)
                    {
                        SuccessfulLogin=postUserLogin(Request,
                                                      RequestContainer,
                                                      ResponseContainer);
                    }
                    else
                    {
                        postUserRegistration(Request,RequestContainer);
                    }
                }
            }            
        }
        catch(IOException | NullPointerException ex) 
        {
            ClientsManager.cleanPreLoginLogs(SocketToRead);
            Processor.cancel(true);
        }
        return SuccessfulLogin;
    }

    /**
     * Reader posts LoginRequest which will be handled by a WorkerProcessor
     * then the WorkerProcessor will give both the reader a response if the
     * request went through and instruct the WorkerWriter to write a message
     * to the socket.
     * @param RequestToMake the login request
     * @param RequestContainer a container for the request
     * @param ExpectedResponse a container for a response from processing
     * of whether the login request was a successful login.
     * @return a boolean representing if the request was successful
     */
    private boolean postUserLogin(LoginRequest RequestToMake,
                                  SimpleQueue<LoginRequest> RequestContainer,
                                  SimpleQueue<Boolean> ExpectedResponse) 
    {
        boolean Response=false;
        RequestContainer.set(RequestToMake);
        try 
        {
            Response=ExpectedResponse.get();
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        return Response;
    }
    
    
    private void postUserRegistration(LoginRequest RequestToMake,
                                      SimpleQueue<LoginRequest> RequestContainer) 
    {
        RequestContainer.set(RequestToMake);
    }
    
    /**
     * A cycle of attempting to read from a socket line-by-line.
     * Fails when a socket dies.
     * Delegates handling and parsing that input to the AuctionsManager,
     * as a Reader does not have access to a TaskPool.
     * Reading and parsing can be independent.
     */
    private void attemptReads() 
    {
        String ToParse;
        BlockingQueue<String> TaskBoard = 
                ClientsManager.FetchClientTaskBoard(User);
        try
        {
            while ((ToParse=SocketInput.readLine())!=null 
                    && postToTaskBoard(TaskBoard, ToParse)) 
            {}
        }
        catch (IOException e) {}
        Processor.cancel(true);
    }
    
    /**
     * Called by a reader to attempt to post an action for a processor to
     * process in a work queue/task board.
     * @param TaskBoard The board/queue of tasks to be processed.
     * @param ToParse The string with the action to perform/process.
     * @return if the posting was successful.
     */
    private boolean postToTaskBoard(BlockingQueue<String> TaskBoard,
                                    String ToParse) 
    {
        boolean PostSuccess=true;
        try 
        {
            PostSuccess=TaskBoard.offer(ToParse, 4, TimeUnit.SECONDS);
        } 
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
            PostSuccess=false;
        }
        return PostSuccess;
    }


    

    
}
