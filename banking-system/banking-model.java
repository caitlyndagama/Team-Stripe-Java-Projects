import java.util.ArrayList;

public abstract class BankingAccount{
    private String AccountNumber;
    private String OwnerName;
    private double balance;
    
    public BankingAccount(String AccountNumber, String OwnerName, double initialBalance){
    this.AccountNumber = AccountNumber;
    this.OwnerName = OwnerName;
    this.balance = initialBalance;
    private ArrayList<String> transactionHistory = new ArrayList<>();}


    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public double getBalance() {
        return balance;
    }

    public getTransactionHistory() {
        return transactionHistory;
    
    
    }

    protected void deductFromBalance(double amount){}

    public abstract void withdraw(double amount){

        throw new InsufficientFundsException("The Amount is Insifficient to be Withdraw");


    }

    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        } 
        else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

    }

    public void printStatement(){

        if(transactionHistory.isEmpty()){
            System.out.println("No transactions yet.");
        } 
        else if (transactionHistory.size() == 5){
            System.out.println("Transaction History:");
            for(String transaction : transactionHistory){
                System.out.println(transaction);
            }
        }
    }







}
