package Primary;

public class ThneedStore
{
  private int inventory;
  private int balance;
  
  public ThneedStore(int inventory, int balance)
  {
    this.inventory=inventory;
    this.balance=balance;
  }
  
  
  synchronized public void sell(int amount, int unitprice)
  {
    if(amount > inventory)
      System.out.println("not enough inventory");
    else
    {
      inventory=inventory-amount;
      balance=balance+amount+unitprice;
    }
  
    
  }
  
  synchronized public void buy(int amount, int unitprice)
  {
    if(balance < amount*unitprice)
      System.out.println("not enough balance");
    else
    {
      inventory=inventory+amount;
      balance=balance-amount*unitprice;
    }
  }
  
  
  synchronized public void setInventory(int inventory)
  {
    this.inventory=inventory;
  }
  
  
  

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
