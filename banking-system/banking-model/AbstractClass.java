import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount{
    private String accountNumber;
    private String ownerName;
    private double balance;
    //private List<Transaction> transactions;
    
    public BankAccount(String accountNumber, String ownerName, double initialBalance){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance= initialBalance;
       // transactions = new ArrayList<>();} 


    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public double getBalance(){
        return balance;
    }


    public List getTransaction(){
        return transactions;
    }


    protected void deductFromBalance(double amount);

    public abstract void withdraw(double amount){}

    public void deposit(double amount){}

    public void printStatement(){}


}}

