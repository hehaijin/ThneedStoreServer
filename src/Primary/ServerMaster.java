package Primary;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerMaster
{
  private ThneedStore thstore;
  private LinkedList<ServerWorker> workers=new LinkedList<>();
  private boolean flag=true;
  
  public ServerMaster()
  {
    thstore=new ThneedStore(this,1000,1000);
       
  }
  
  
  public void notifyWorkers()
  {
    for(ServerWorker sw: workers)
    {
      sw.notifyClient();
    }
  }
  
  public void init() throws Exception
  {
    
    ServerSocket ss=new ServerSocket(6666);
    System.out.println("Server socket established.");
    
    while(flag)
    {
      Socket s=ss.accept();
      ServerWorker sw=new ServerWorker(thstore,s);
      workers.add(sw);
      System.out.println("a new worker is generated");
    }
   ss.close();
  }
  
  
  public void end()
  {
    flag=false;
  }
  

  public static void main(String[] args) throws Exception
  {
    // TODO Auto-generated method stub
    ServerMaster sm=new ServerMaster();
    sm.init();
    
    
  }

}
