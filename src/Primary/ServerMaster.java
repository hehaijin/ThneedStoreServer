package Primary;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * the main class for the Thneed store
 * @author Haijin He
 *
 */
public class ServerMaster
{
  int port;
  private ThneedStore thstore;
  private LinkedList<ServerWorker> workers=new LinkedList<>();
  private boolean flag=true;
  long time=0;
  
  public ServerMaster(int port)
  {
    thstore=new ThneedStore(this,1000,1000); 
    this.time=System.currentTimeMillis();
    this.port=port;
  }
  
  /**
   * notify all worker threads of store status change.
   */
  public void notifyWorkers()
  {
    for(ServerWorker sw: workers)
    {
      sw.notifyClient();
    }
  }
  
  /**
   * this method starts the server socket and listen for client connection.
   * @throws Exception
   */
  public void init() throws Exception
  {
    
    ServerSocket ss=new ServerSocket(port);
    System.out.println("Server established.");
    
    while(flag)
    {
      Socket s=ss.accept();
      ServerWorker sw=new ServerWorker(this,thstore,s);
      workers.add(sw);
      sw.setIndex(workers.indexOf(sw));
      System.out.println("A new client is connected.");
      System.out.println("Now total "+workers.size()+ " workers.");
      
      
    }
   ss.close();
  }
  
  public void removeWorker(ServerWorker sw)
  {
    workers.remove(sw);
    System.out.println("a worker is removed");
    System.out.println(workers.size()+ " works left.");
  }
  
  
  public void end()
  {
    flag=false;
  }
  

  public static void main(String[] args) throws Exception
  {
    // TODO Auto-generated method stub
    int port=Integer.parseInt(args[0]);
    ServerMaster sm=new ServerMaster(port);
    sm.init();
    
    
  }

}
