package Primary;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerMaster
{
  private ThneedStore thstore;
  private LinkedList<ServerWorker> workers=new LinkedList<>();
  
  public ServerMaster()
  {
    thstore=new ThneedStore(1000,1000);
       
  }
  
  
  public void notifyWorkers()
  {
    for(ServerWorker sw: workers)
    {
      sw.notifyClient();
    }
  }
  
  
  

  public static void main(String[] args) throws Exception
  {
    // TODO Auto-generated method stub
    ServerMaster sm=new ServerMaster();
    ServerSocket ss=new ServerSocket(6666);
    while(true)
    {
      Socket s=ss.accept();
      ServerWorker sw=new ServerWorker(sm.thstore,s);
      sm.workers.add(sw);
    }
    
    
    
  }

}
