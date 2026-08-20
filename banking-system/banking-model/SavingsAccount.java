package banking.model;

public class SavingsAccount extends AbstractClass {

    private final double minimumBalance;

    public SavingsAccount(
            String accountNumber,
            String ownerName,
            double initialBalance,
            double minimumBalance) {

        super(accountNumber, ownerName, initialBalance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
        }

        if (getBalance() - amount < minimumBalance) {
            throw new InsufficientFundsException(
                    amount,
                    getBalance() - minimumBalance);
        }

        deductFromBalance(amount);

        
        addTransaction(new Transaction(
                Transaction.Type.WITHDRAWAL,
                amount));
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountNumber='" + getAccountNumber() + '\'' +
                ", owner='" + getOwnerName() + '\'' +
                ", balance=" + getBalance() +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}