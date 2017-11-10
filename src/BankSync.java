/*Concept of Multi threading and synchronization has been implemented here
 * Process of depositing and withdrawing has been shown. Initially balance is taken as 500 and every time
 * person withdraws money Rs 50 are deducted and when he deposits money Rs 100 are deposited
 * Whole process uses Synchronization which has been implemented below where methods for withdrawing and depositing
 * are synchronized.
 */


public class BankSync implements Runnable
{
	Account acc = new Account(); //creating object of Account class
	
	public static void main(String[] args) 
	{
		BankSync ts = new BankSync();  //creating object of class which implements Runnable interface
		
		//creating object for thread class
		
		Thread t1 = new Thread(ts, "person 1"); 
        Thread t2 = new Thread(ts, "person 2");
        
        //starting newly created threads
        
        t1.start();
        t2.start();

	}
	
	 @Override
	 public void run()  //Run method used for performing actions for given thread
	 {
        for (int i = 0; i < 2; i++) 
        {
            reqWithdraw(50);  //withdrawing 50
            if (acc.getBal() < 0) 
            {
                System.out.println("low account balance !");
            }
            reqDeposit(100); //depositing 100
        }
	    
	 }

    // synchronized reqWithdraw method
    private synchronized void reqWithdraw(int bal) 
    {
        if (acc.getBal()>=bal) 
        {
            System.out.println(Thread.currentThread().getName()+" "+ "is trying to withdraw");

            try 
            {
                Thread.sleep(100);   //currently executing thread sleeps for 100 milliseconds
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            acc.withdraw(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "has completed withdrawing"+" Now, Balance is: " +acc.getBal());
            System.out.println("------------------------------------------------------------------------");
        }
        else
        {        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for depositing ");
        }
    }

 // synchronized reqDeposit method
    private synchronized void reqDeposit(int bal)
    {
        if (bal>0) 
        {
            System.out.println(Thread.currentThread().getName()+" "+ " is trying to deposit");
            try 
            {
                Thread.sleep(100);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            acc.deposit(bal);
            System.out.println(Thread.currentThread().getName()+" "+ "has completed depositing"+" Now,Balance is: " +acc.getBal());
            System.out.println("------------------------------------------------------------------------");
        }
        else
        {        
            System.out.println(Thread.currentThread().getName()+ " "+"doesn't have enough money for depositing");
        }
    }

}

class Account //Account class
{
    int balance= 500;  //initial balance

    public int getBal()  //method which returns balance
    {
        return balance;
    }

    public void withdraw(int bal) //withdraw method of Account class
    {
        balance= balance-bal;  //calculating balance after withdraw
    }

    public void deposit(int bal) //deposit method of Account class
    {
        balance= balance+bal;  //calculating balance after deposit
    }
}