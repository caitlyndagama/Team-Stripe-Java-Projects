

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    protected void deductFromBalance(double amount) {
        balance -= amount;
    }

    protected void addToBalance(double amount) {
        balance += amount;
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        addToBalance(amount);
        transactions.add(new Transaction("Deposit", amount));
    }

    public void printStatement() {
        System.out.println("Account Statement");
        System.out.println("-----------------");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Balance: " + balance);
        System.out.println();
        System.out.println("Recent Transactions:");

        int start = Math.max(0, transactions.size() - 5);

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (int i = start; i < transactions.size(); i++) {
                System.out.println(transactions.get(i));
            }
        }
    }
}