package banking.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // username -> password
    private static final Map<String, String> USER_PASSWORDS = new HashMap<>();
    // username -> role 
    private static final Map<String, String> USER_ROLES = new HashMap<>();
    // username -> their account
    private static final Map<String, BankAccount> USER_ACCOUNTS = new HashMap<>();

    static {
        USER_PASSWORDS.put("alice", "alice123");
        USER_PASSWORDS.put("bob", "bob123");
        USER_PASSWORDS.put("teller1", "teller123");

        USER_ROLES.put("alice", "CUSTOMER");
        USER_ROLES.put("bob", "CUSTOMER");
        USER_ROLES.put("teller1", "TELLER");

        USER_ACCOUNTS.put("alice", new SavingsAccount("SA-1001", "Alice", 1000.00, 100.00));
        USER_ACCOUNTS.put("bob", new CurrentAccount("CA-2001", "Bob", 500.00, 200.00));
        // teller1 has no account, just logs in as staff
    }

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("        Welcome to Stripe Bank       ");
        System.out.println("=====================================");

        boolean appRunning = true;
        while (appRunning) {
            String username = login();

            if (username == null) {
                // user typed exit at login
                appRunning = false;
                break;
            }

            String role = USER_ROLES.get(username);
            if ("TELLER".equals(role)) {
                tellerMenu(username);
            } else {
                customerMenu(username);
            }
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    // keeps asking for username/password until correct
    private static String login() {
        while (true) {
            System.out.println();
            System.out.println("Login (type 'exit' to quit)");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            if (username.equalsIgnoreCase("exit")) {
                return null;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if (USER_PASSWORDS.containsKey(username)
                    && USER_PASSWORDS.get(username).equals(password)) {
                System.out.println("Login successful. Welcome, " + username + "!");
                return username;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }

    private static void customerMenu(String username) {
        boolean loggedIn = true;

        while (loggedIn) {
            BankAccount account = USER_ACCOUNTS.get(username);

            // just in case, doesn't crash if account somehow missing
            if (account == null) {
                System.out.println("Account not found for user '" + username + "'. Logging out.");
                return;
            }

            System.out.println();
            System.out.println("Customer Menu (" + username + ")");
            System.out.println("[1] View Balance");
            System.out.println("[2] Deposit");
            System.out.println("[3] Withdraw");
            System.out.println("[4] Transaction History");
            System.out.println("[5] Logout");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewBalance(account);
                    break;
                case 2:
                    deposit(account);
                    break;
                case 3:
                    withdraw(account);
                    break;
                case 4:
                    viewTransactionHistory(account);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    // lets teller manage any customer account by username
    private static void tellerMenu(String username) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("Teller Menu (" + username + ")");
            System.out.println("[1] View Customer Balance");
            System.out.println("[2] Deposit to Customer Account");
            System.out.println("[3] Withdraw from Customer Account");
            System.out.println("[4] View Customer Transaction History");
            System.out.println("[5] Logout");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 5) {
                System.out.println("Logging out...");
                loggedIn = false;
                continue;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid option. Please choose 1-5.");
                continue;
            }

            System.out.print("Enter customer username: ");
            String customerUsername = scanner.nextLine().trim();
            BankAccount account = USER_ACCOUNTS.get(customerUsername);

            if (account == null) {
                System.out.println("Account not found for user '" + customerUsername + "'.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewBalance(account);
                    break;
                case 2:
                    deposit(account);
                    break;
                case 3:
                    withdraw(account);
                    break;
                case 4:
                    viewTransactionHistory(account);
                    break;
            }
        }
    }

    private static void viewBalance(BankAccount account) {
        System.out.printf("Current balance: R %.2f%n", account.getBalance());
    }

    private static void deposit(BankAccount account) {
        System.out.print("Enter deposit amount: ");
        String input = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        try {
            account.deposit(amount);
            System.out.printf("Deposit successful. New balance: R %.2f%n", account.getBalance());
        } catch (IllegalArgumentException e) {
            // happens if amount is 0 or negative
            System.out.println(e.getMessage());
        }
    }

    private static void withdraw(BankAccount account) {
        System.out.print("Enter withdrawal amount: ");
        String input = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful. New balance: R %.2f%n", account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewTransactionHistory(BankAccount account) {
        account.printStatement();
    }
}
