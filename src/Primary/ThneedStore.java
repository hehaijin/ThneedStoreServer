package Primary;

/**
 * Thneed store class. 
 * @author Haijin He
 *
 */
public class ThneedStore
{
  private int inventory;
  private double balance;
  private ServerMaster sm=null;
  
  /**
   * constructor 
   * @param sm
   * @param inventory
   * @param balance
   */
  public ThneedStore(ServerMaster sm,int inventory, double balance)
  {
    this.inventory=inventory;
    this.balance=balance;
    this.sm=sm;
  }
  
  
  synchronized public boolean sell(int amount, double unitprice)
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
  
  synchronized public boolean buy(int amount, double unitprice)
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
  
 
  
   public int getInventory()
  {
    return inventory;
  }
  
   public double getBalance()
  {
    return balance;
  }

  
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
