package banking.model;

// thrown when a withdrawal can't be completed - due to insufficient funds
public class InsufficientFundsException extends Exception {

    // difference between what's requested and what's available
    private final double shortfall;

    //  amountRequested - the amount the user tried to withdraw
    // availableBalance the current balance in the account
    public InsufficientFundsException(double amountRequested, double availableBalance) {
        this.shortfall = amountRequested - availableBalance;
    }
     
    @Override
    public String getMessage() {
        return String.format("Insufficient funds. You need R %.2f more to complete this withdrawal.", shortfall);
    }

    // returns raw shortfall value
    public double getShortfall() {
        return shortfall;
    }
}
