package Primary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorker extends Thread
{
  private ThneedStore thstore;
  private Socket socket;
  private boolean flag = true;
  private Scanner sc=null;
  private PrintStream ps=null;

  public ServerWorker(ThneedStore thstore, Socket socket) throws Exception
  {
    this.thstore = thstore;
    this.socket = socket;
   
    sc=new Scanner(socket.getInputStream());
    this.start();
    ps=new PrintStream(socket.getOutputStream());
    ps.println("status: "+thstore.getInventory()+ " "+ thstore.getBalance());
    
  }

  @Override
  public void run()
  {
    // TODO Auto-generated method stub
    // BufferedInputStream bf=null;
 
    while (flag)
    {
      String command = sc.next();
      if (command.equals("buy:"))
      {
        String amountInString = sc.next();
        String upInString = sc.next();
        int amount = Integer.parseInt(amountInString);
        int up = Integer.parseInt(upInString);
        System.out.println("client is buying "+amount+" "+up);
       
        thstore.buy(amount, up);
        

      } else if (command.equals("sell:"))
      {

      } else
      {
        System.out.println("Wrong input for the serverworker!");
      }

    }

  }

  public void notifyClient()
  {
    ps.println("Store information updated!");
    ps.println("status:"+thstore.getInventory()+ " "+ thstore.getBalance());  
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
