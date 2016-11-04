package Primary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorker extends Thread
{
  private ThneedStore thstore;
  private Socket socket;
  private boolean flag = true;

  public ServerWorker(ThneedStore thstore, Socket socket)
  {
    this.thstore = thstore;
    this.socket = socket;
  }

  @Override
  public void run()
  {
    // TODO Auto-generated method stub
    // BufferedInputStream bf=null;
    Scanner sc = null;
    try
    {
      // bf=new BufferedInputStream(socket.getInputStream());
      sc = new Scanner(socket.getInputStream());
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    while (flag)
    {
      String command = sc.next();
      if (command.equals("buy:"))
      {
        String amountInString = sc.next();
        String upInString = sc.next();
        int amount = Integer.parseInt(amountInString);
        int up = Integer.parseInt(upInString);
        sc.nextLine();
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

  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
