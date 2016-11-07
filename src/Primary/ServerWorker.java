package Primary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * the serverworkers class coresponds to each connecting client.
 * @author Haijin He
 *
 */
public class ServerWorker extends Thread
{
  private ServerMaster sm;
  private ThneedStore thstore;
  private Socket socket;
  private boolean flag = true;
  private Scanner sc=null;
  private PrintStream ps=null;
  private int index;
 

  public ServerWorker(ServerMaster sm,ThneedStore thstore, Socket socket) throws Exception
  {
    this.sm=sm;
    this.thstore = thstore;
    this.socket = socket;
   
    sc=new Scanner(socket.getInputStream());
    this.start();
    ps=new PrintStream(socket.getOutputStream());
    ps.println("Welcome to the Thneed store.");
   // ps.println("status: "+(System.currentTimeMillis()-sm.time)/1000 +" "+"inventory="+thstore.getInventory()+ " "+ "treasury="+thstore.getBalance()); 
    ps.printf("status: %d: inventory=%d treasury=%.2f\n",(System.currentTimeMillis()-sm.time)/1000,thstore.getInventory(),thstore.getBalance());
   
  }

  @Override
  public void run() 
  {
    // TODO Auto-generated method stub
    // BufferedInputStream bf=null;
 
    while (flag)
    {
      String command=null;
     
      try
      {
        command= sc.next();
      if (command.equals("buy:"))
      {
        String amountInString = sc.next();
        String upInString = sc.next();
        int amount = Integer.parseInt(amountInString);
        //int up = Integer.parseInt(upInString);
        double up=Double.parseDouble(upInString);
        System.out.println("client "+index+" is buying "+amount+" "+up);
       
        boolean result=thstore.buy(amount, up);
        if(!result)
        {
          ps.println("Thneed store denied your request!");
        }
        else
        {
          ps.println("Transaction successful!");
        }
        

      } else if (command.equals("sell:"))
      {
        String amountInString = sc.next();
        String upInString = sc.next();
        int amount = Integer.parseInt(amountInString);
       // int up = Integer.parseInt(upInString);
        double up=Double.parseDouble(upInString);
        System.out.println("client "+index+" is selling "+amount+" "+up);
       
        boolean result=thstore.sell(amount, up);    
        if(!result)
        {
          ps.println("Thneed store denied your request!");
        }
        else
        {
          ps.println("Transaction successful!");
        }
        
      } else
      {
        System.out.println("Wrong input for the serverworker!");
      }
      
      }
      catch(Exception e)
      {
         
         sm.removeWorker(this);
         flag=false;
      }
    }
  }

  /**
   * notify client of store information update.
   */
  public void notifyClient()
  {
    ps.println("Store information updated!");
    //ps.println("status: "+(System.currentTimeMillis()-sm.time)/1000 +" "+"inventory="+thstore.getInventory()+ " "+ "treasury="+thstore.getBalance()); 
    ps.printf("status: %d: inventory=%d treasury=%.2f\n",(System.currentTimeMillis()-sm.time)/1000,thstore.getInventory(),thstore.getBalance());

  }
  
  /**
   * sets the index of the worker in the servermaster list.
   * @param index
   */
  public void setIndex(int index)
  {
    this.index=index;
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
