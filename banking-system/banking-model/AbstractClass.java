package banking.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount{
    // Private Fields;

    private String accountNumber;
    private String ownerName;
    private double balance;
    // Transactions List from Tickets 2

    //private List<Transaction> transactions;
    
    public BankAccount(String accountNumber, String ownerName, double initialBalance){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance= initialBalance;
        // initialise the transaction list;

       // transactions = new ArrayList<>();
       } 


        // Some Getters to access the Private Attributes

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public double getBalance(){
        return balance;
    }


    //public List getTransaction(){
    //    return transactions;
    //}


    protected void deductFromBalance(double amount){
        balance-= amount;
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void deposit(double amount){
                if (amount <= 0) {
            System.out.println("Deposit must be greater than zero.");
        }

        balance += amount;

        // Record transaction
        //transactions.add(new Transaction("Deposit", amount));
    }

    

    public void printStatement(){
        System.out.println("Last 5 Transactions:");
        for(int i = 0; i < transactions.size();i++){
            System.out.println(transactions.get(i));
        }

    }
}




