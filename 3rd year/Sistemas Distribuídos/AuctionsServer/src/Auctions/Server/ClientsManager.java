/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auctions.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Every ClientsManager is a manager for the clients in an auction house.
 * It manages accesses to all relevant client data.
 * Keeps temporary logs for not yet authenticated sockets for exception writing.
 * Keeps ClientLogs as a repository for offline clients.
 * It implements Observer so it can be notified by a AuctionsManager of when
 * an auction ends.
 * @author André Diogo, Gonçalo Pereira, António Silva
 */
public class ClientsManager implements Observer
{
    //To be replaced by ??protobuffers??
    private final Map<String,String> Clients;
    private final Map<String,BlockingQueue<String>> ClientLogs;
    private final Map<String,Socket> ActiveSockets;
    private final Map<Socket,SimpleQueue<LoginRequest>> SocketToProcessorRequests;
    private final Map<Socket,SimpleQueue<Boolean>> ProcessorToSocketResponses;
    private final Map<Socket,SimpleQueue<String>> ProcessorToWriterRequests;
    private final Map<Socket,SimpleQueue<String>> ProcessorToWriterUsers;
    private final Map<String,BlockingQueue<String>> ClientTaskBoards;
    private final ExecutorService TaskPool;
    
    public ClientsManager(ExecutorService TaskPool) 
    {
        Clients = new HashMap<>(2048);
        ClientLogs = new HashMap<>();
        ActiveSockets= new HashMap<>();
        SocketToProcessorRequests = new HashMap<>();
        ProcessorToWriterRequests = new HashMap<>();
        ProcessorToSocketResponses = new HashMap<>();
        ProcessorToWriterUsers = new HashMap<>();
        ClientTaskBoards = new HashMap<>();
        this.TaskPool = TaskPool;
    }

    /**
     * Gets the ProcessorToWriterUser for a given Socket, so the worker writer 
     * can be informed of the logged in user.
     * @param RequestSocket the socket attempting authentication
     * @return the corresponding ProcessorToWriterRequest.
     */    
    protected SimpleQueue<String> getProcessorToWriterUser(Socket RequestSocket)
    {
        synchronized(ProcessorToWriterUsers) 
        {
            return ProcessorToWriterUsers.get(RequestSocket);
        } 
    }
    
    /**
     * Gets the SocketToProcessorRequest for a given Socket.
     * @param RequestSocket the socket attempting authentication
     * @return the corresponding SocketToProcessorRequest.
     */
    protected SimpleQueue<LoginRequest> getSocketToProcessorRequest(Socket RequestSocket)
    {
        synchronized(SocketToProcessorRequests) 
        {
           return SocketToProcessorRequests.get(RequestSocket);
        }
    }

    /**
     * Gets the ProcessorToSocketResponse for a given Socket.
     * @param RequestSocket the socket attempting authentication
     * @return the corresponding ProcessorToSocketResponse.
     */
    protected SimpleQueue<Boolean> getProcessorToSocketResponse(Socket RequestSocket)
    {
        synchronized(ProcessorToSocketResponses) 
        {
           return ProcessorToSocketResponses.get(RequestSocket);
        }
    }
    
    /**
     * Gets the ProcessorToWriterRequest for a given Socket.
     * @param RequestSocket the socket attempting authentication
     * @return the corresponding ProcessorToWriterRequest.
     */
    protected SimpleQueue<String> getProcessorToWriterRequest(Socket RequestSocket)
    {
        synchronized(ProcessorToWriterRequests) 
        {
           return ProcessorToWriterRequests.get(RequestSocket);
        }
    }

    
    /**
     * Called by a factory to acknowledge that there is a new unauthenticated
     * user that needs a ProcessorToWriterRequest queue, a
     * SocketToProcessorRequests queue and a ProcessorToSocketResponses queue.
     * @param RequestSocket the socket which needs authentication
     */
    protected void acknowledgeSocket(Socket RequestSocket) 
    {
        synchronized(SocketToProcessorRequests) 
        {
            SocketToProcessorRequests.put(RequestSocket, new SimpleQueue<>());
        }
        synchronized(ProcessorToSocketResponses) 
        {
            ProcessorToSocketResponses.put(RequestSocket, new SimpleQueue<>());
        }
        synchronized(ProcessorToWriterRequests) 
        {
            ProcessorToWriterRequests.put(RequestSocket, new SimpleQueue<>());
        }
        synchronized(ProcessorToWriterUsers) 
        {
            ProcessorToWriterUsers.put(RequestSocket, new SimpleQueue<>());
        }
    }
    

    /**
     * When an unauthenticated user disconnects or finally logs in successfully
     * remove his Request queues.
     * @param RequestSocket the disconnected socket to remove logs for 
     */
    protected void cleanPreLoginLogs(Socket RequestSocket)
    {
        synchronized(ProcessorToWriterRequests) 
        {
            if(ProcessorToWriterRequests.containsKey(RequestSocket)) 
            {
                ProcessorToWriterRequests.remove(RequestSocket);
            }
        }
        synchronized(SocketToProcessorRequests) 
        {
            if(SocketToProcessorRequests.containsKey(RequestSocket)) 
            {
                SocketToProcessorRequests.remove(RequestSocket);
            }        
        }
        synchronized(ProcessorToSocketResponses) 
        {
            if(ProcessorToSocketResponses.containsKey(RequestSocket)) 
            {
                ProcessorToSocketResponses.remove(RequestSocket);
            }        
        }
        synchronized(ProcessorToWriterUsers) 
        {
            if(ProcessorToWriterUsers.containsKey(RequestSocket)) 
            {
                ProcessorToWriterUsers.remove(RequestSocket);
            }
        }        
    }

    /**
     * Fetch a ClientLog for a given User.
     * @param User A client Username
     * @return The Log
     */
    protected BlockingQueue<String> FetchClientLog(String User) 
    {
        BlockingQueue<String> Log;
        synchronized(ClientLogs) 
        {
            Log=ClientLogs.get(User);
        }
        return Log;            
    }

    /**
     * Fetch a ClientLog for a given User.
     * @param User A client Username
     * @return The Log
     */
    protected BlockingQueue<String> FetchClientTaskBoard(String User) 
    {
        BlockingQueue<String> Log;
        synchronized(ClientTaskBoards) 
        {
            Log=ClientTaskBoards.get(User);
        }
        return Log;
    }

    
    /**
     * Attempts to register a user, otherwise writes to the queue of what
     * to write to socket.
     * @param Request the registration request
     * @param ToWriteContainer the queue of what to write to socket
     */
    public void registerUser(LoginRequest Request,
                             SimpleQueue<String> ToWriteContainer) 
    {
        boolean ClientExists;
        String Username=Request.getUsername();
        String Password;
        synchronized(this.Clients) 
        {
            ClientExists=Clients.containsKey(Username);
        }
        if (ClientExists) 
        {            
            //In deployment should have a localization layer.
            ToWriteContainer.set("Utilizador já registado");
        }
        else 
        {
            Password=Request.getPassword();
            synchronized(this.Clients)
            {
                Clients.put(Username, Password);
            }
            //In deployment should have a localization layer.
            ToWriteContainer.set("Registo efetuado com sucesso");
        }
    }

    /**
     * Attempts to login a user and write an "OK" to the queue of what
     * to write to socket, otherwise writes a response of what 
     * went wrong to said queue.
     * @param Request the login request
     * @param ToWriteContainer the queue of what to write to socket
     * @return returns a boolean expressing the success in logging in.
     */
    public boolean loginUser(LoginRequest Request, 
                             SimpleQueue<String> ToWriteContainer,
                             SimpleQueue<String> UserContainer) 
    {
        String Username=Request.getUsername();
        String Password;
        boolean SuccessfulLogin = true;
        boolean ClientExists;
        synchronized(this.Clients) 
        {
            ClientExists=Clients.containsKey(Username);
        }    
        if (!ClientExists) 
        {
            ToWriteContainer.set("NOT");
            SuccessfulLogin=false;
        }
        else 
        { 
            Password=Request.getPassword();
            String PasswordToMatch;
            synchronized(this.Clients) 
            {
                PasswordToMatch=Clients.get(Username);
            }
            if (!PasswordToMatch.equals(Password)) 
            {
                //In deployment should have a localization layer.
                ToWriteContainer.set("NOT");
                SuccessfulLogin=false;
            }            
        }
        //On login register client as active, if it's not active already
        //otherwise kick the previous login out.
        if (SuccessfulLogin) 
        {
            boolean AlreadyLoggedIn;
            synchronized(this.ActiveSockets) 
            {
                AlreadyLoggedIn = ActiveSockets.containsKey(Username);
            }
            if (AlreadyLoggedIn) 
            {
                Socket ToDisconnect;
                synchronized(this.ActiveSockets) 
                {
                    ToDisconnect = ActiveSockets.get(Username);
                }
                try 
                {
                    ToDisconnect.close();
                }
                catch(IOException e) 
                {
                    e.printStackTrace();
                }
            }
            else 
            {
                synchronized(this.ClientLogs) 
                {
                    ClientLogs.put(Username, new LinkedBlockingQueue<>(64));
                }
            }
            ActiveSockets.put(Username, Request.getRequestSocket());
            BlockingQueue<String> ClientTaskBoard = new ArrayBlockingQueue<>(64);
            synchronized(this.ClientTaskBoards) 
            {
                ClientTaskBoards.put(Username, ClientTaskBoard);
            }
            //In deployment should have a localization layer.
            ToWriteContainer.set("OK");
            UserContainer.set(Username);
        }
        return SuccessfulLogin;
    }

    /**
     * Update runs a new task to write to every ClientLog for each bidder in the
     * received Auction. 
     * @param o The Auctions Manager that is being observed
     * @param arg The Auction that just ended
     */
    @Override
    public void update(Observable o, Object arg)
    {
        //Throws a runtime exception if the Auction is already over by the time
        //it gets there.
        TaskPool.submit(()->WriteToClientLogs((AuctionsManager)o,(Auction)arg));        
    }

    /**
     * Technically has consistency problems because someone can be
     * writing to the bidders when it iterates through other bidders
     * that aren't the highest at that point, but those who lose
     * won't be shown their losing bid so it doesn't matter.
     * @param AuctionsManager the AuctionsManager from which the 
     * notification came
     * @param Auction the Auction that just ended
     */
    private void WriteToClientLogs(AuctionsManager AuctionsManager,
                                   Auction Auction) 
    {
        boolean isStillActive=true;
        synchronized(Auction) 
        {
            if(isStillActive=Auction.isActive()) 
            {
                Auction.flagInactive();
            }
        }
        if (isStillActive) 
        {
            long AuctionNumber=Auction.getAuctionNumber();
            TreeSet<Bid> Bids=Auction.getBids();
            StringBuilder sb=new StringBuilder();
            String User;
            Bid HighestBid;    
            Future<BlockingQueue> Log;
            Future<?> WriteComputationResult;
            HighestBid = Auction.highestBid();
            String Auctioneer=Auction.getAuctioneer();
            TaskPool.submit(()->WriteToAuctioneer(Auctioneer));
            if (HighestBid!=null)
            {
                float BidAmount=HighestBid.getBid();
                User=HighestBid.getUser();
                Log=TaskPool.submit(()->FetchClientLog(User));
                WriteComputationResult=
                    TaskPool.submit(()->WriteToEachClient(Auctioneer,
                                                          Bids,
                                                          User,
                                                          AuctionNumber,
                                                          BidAmount));
                //Should check before updating if there is a need to notify
                //If it fails here it's an exception
                try 
                {
                    sb.append("Ganhou o leilão ")
                      .append(AuctionNumber)
                      .append(", ")
                      .append(User)
                      .append(" com a proposta de ")
                      .append(HighestBid.getBid());
                    Log.get().add(sb.toString());
                    WriteComputationResult.get();
                    AuctionsManager.removeAuction(AuctionNumber);
                } 
                catch (InterruptedException 
                        | ExecutionException | NullPointerException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
        else 
        {
            throw new InactiveAuctionException();
        }
    }

    
    /**
     * 
     * @param Bids the Bids on the auction.
     * @param HighestBidder the Username which won the auction.
     * @param AuctionNumber the auction's identifier.
     */
    private void WriteToEachClient(String Auctioneer,
                                   TreeSet<Bid> Bids,
                                   String HighestBidder,
                                   long AuctionNumber,
                                   float BidAmount) 
    {
        final Future<String> ToLog;
        ToLog=TaskPool.submit(()->(CreateLosingAuctionString(HighestBidder,
                                                             AuctionNumber,
                                                             BidAmount)));
        TaskPool.submit(()->WriteToAuctioneer(Auctioneer,
                                              HighestBidder,
                                              AuctionNumber,
                                              BidAmount));
        synchronized(Bids)
        {
            Bids.stream().forEach(b->TaskPool.submit(()->WriteToClient(ToLog,
                                                                       b.getUser())));
        }
    }

    /**
     * Generates a String to output to all auction bidder who did no win.
     * @param HighestBidder the username which won the auction
     * @param AuctionNumber the auction's identifier.
     * @return the output string
     */
    private String CreateLosingAuctionString(String HighestBidder,
                                             long AuctionNumber,
                                             float BidAmount) {
        StringBuilder sb=new StringBuilder();                
        sb.append(HighestBidder)
          .append("ganhou o leilão ")
          .append(AuctionNumber)
          .append(", em que participou com a oferta: ")
          .append(BidAmount);
        return sb.toString();
    }

    /**
     * A simple task to write an Auction String to a bidder.
     * @param ToLog A string that is possibly 
     * being computed still asynchronously.
     * @param Bidder the Bidder to notify.
     */
    private void WriteToClient(Future<String> ToLog, String Bidder) 
    {
        try 
        {
            if (Bidder!=null) 
            {
                FetchClientLog(Bidder).put(ToLog.get());
            }
        }
        catch (InterruptedException 
                | ExecutionException ex) 
        {
            ex.printStackTrace();
        }
    }
    


    protected void atSocketDisconnected(String User) 
    {
        synchronized(this.ActiveSockets) 
        {
            ActiveSockets.remove(User);
        }
        synchronized(this.ClientTaskBoards) 
        {
            if (ClientTaskBoards.containsKey(User)) 
            {
                BlockingQueue<String> ClientTaskBoard=
                        ClientTaskBoards.get(User);
                ClientTaskBoard=null;
            }
            ClientTaskBoards.remove(User);
        }
    }

    /**
     * A simple task to write an Auction String to an auctioneer.
     * @param Auctioneer the auctioneer's username
     */
    private void WriteToAuctioneer(String Auctioneer) 
    {
        String ToLog="Leilão Terminado";
        if (Auctioneer!=null) 
        {
            try 
            {
                FetchClientLog(Auctioneer).put(ToLog);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * A simple task to write an Auction String to an auctioneer with the indication
     * of who won the auction.
     * @param Auctioneer the auctioneer's username
     */
    private void WriteToAuctioneer(String Auctioneer,
                                   String HighestBidder,
                                   long AuctionNumber,
                                   float BidAmount) 
    {
        Future<String> ToLog=
                TaskPool.submit(()->CreateAuctionTerminationString(HighestBidder,
                                                                   AuctionNumber,
                                                                   BidAmount));
        WriteToClient(ToLog,Auctioneer);
    }

    private String CreateAuctionTerminationString(String HighestBidder,
                                                  long AuctionNumber,
                                                  float BidAmount) 
    {
        StringBuilder sb=new StringBuilder();                
        sb.append(HighestBidder)
          .append(" ganhou o seu leilão - ")
          .append(AuctionNumber)
          .append(" - com a oferta de: ")
          .append(BidAmount);
        return sb.toString();
    }

}
