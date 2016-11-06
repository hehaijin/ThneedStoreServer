package Primary;

public class ThneedStore
{
  private int inventory;
  private int balance;
  private ServerMaster sm=null;
  
  public ThneedStore(ServerMaster sm,int inventory, int balance)
  {
    this.inventory=inventory;
    this.balance=balance;
    this.sm=sm;
  }
  
  
  synchronized public boolean sell(int amount, int unitprice)
  {
    if(amount > inventory)
      {
      System.out.println("not enough inventory");
      return false;
      }
    else
    {
      inventory=inventory-amount;
      balance=balance+amount*unitprice;
      sm.notifyWorkers();
    }
  
    return true;
  }
  
  synchronized public boolean buy(int amount, int unitprice)
  {
    if(balance < amount*unitprice)
    {
      System.out.println("not enough balance");
      return false;
    }
    
    else
    {
      inventory=inventory+amount;
      balance=balance-amount*unitprice;
      sm.notifyWorkers();
    }
    return true;
  }
  
  
   public void setInventory(int inventory)
  {
    this.inventory=inventory;
  }
  
  
   public int getInventory()
  {
    return inventory;
  }
  
   public int getBalance()
  {
    return balance;
  }

  
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
