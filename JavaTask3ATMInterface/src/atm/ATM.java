package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;
    private Account currentAccount;

    private ArrayList<Transaction> transactions;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
        transactions = new ArrayList<>();
    }

    public void start() {

        System.out.println("=================================");
        System.out.println("        WELCOME TO ATM");
        System.out.println("=================================");

        if (!login()) {
            System.out.println("\nAccess denied.");
            System.out.println("Thank you for using our ATM.");
            return;
        }

        showMenu();
    }

    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.findAccount(userId);

            if (account != null && account.getPin().equals(pin)) {

                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, " + currentAccount.getUserId());

                return true;
            }

            attempts++;

            System.out.println("Incorrect User ID or PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }

        System.out.println("\nMaximum login attempts exceeded.");
        return false;
    }

    private void showMenu() {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("           ATM MENU");
            System.out.println("=================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showTransactionHistory();
                    break;

                case "2":
                    withdraw();
                    break;

                case "3":
                    deposit();
                    break;

                case "4":
                    transfer();
                    break;

                case "5":
                    System.out.println("\nThank you for using our ATM.");
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {

        System.out.println("\n=================================");
        System.out.println("       TRANSACTION HISTORY");
        System.out.println("=================================");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {

        System.out.println("\n---------- WITHDRAW ----------");

        System.out.printf("Current Balance: ₹%.2f%n",
                currentAccount.getBalance());

        System.out.print("Enter withdrawal amount: ");

        try {

            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            if (amount > currentAccount.getBalance()) {
                System.out.println("Insufficient Funds");
                return;
            }

            currentAccount.withdraw(amount);

            transactions.add(
                    new Transaction(
                            "WITHDRAW",
                            amount,
                            currentAccount.getBalance(),
                            "Cash withdrawal"
                    )
            );

            System.out.println("Withdrawal successful.");
            System.out.printf("Remaining Balance: ₹%.2f%n",
                    currentAccount.getBalance());

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
        }
    }

    private void deposit() {

        System.out.println("\n---------- DEPOSIT ----------");

        System.out.printf("Current Balance: ₹%.2f%n",
                currentAccount.getBalance());

        System.out.print("Enter deposit amount: ");

        try {

            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            currentAccount.deposit(amount);

            transactions.add(
                    new Transaction(
                            "DEPOSIT",
                            amount,
                            currentAccount.getBalance(),
                            "Cash deposit"
                    )
            );

            System.out.println("Deposit successful.");
            System.out.printf("New Balance: ₹%.2f%n",
                    currentAccount.getBalance());

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
        }
    }

    private void transfer() {

        System.out.println("\n---------- TRANSFER ----------");

        System.out.print("Enter recipient account ID: ");
        String recipientId = scanner.nextLine();

        Account recipient = bank.findAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (recipient == currentAccount) {
            System.out.println("You cannot transfer money to your own account.");
            return;
        }

        System.out.print("Enter transfer amount: ");

        try {

            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            if (amount > currentAccount.getBalance()) {
                System.out.println("Insufficient Funds");
                return;
            }

            currentAccount.withdraw(amount);
            recipient.deposit(amount);

            transactions.add(
                    new Transaction(
                            "TRANSFER",
                            amount,
                            currentAccount.getBalance(),
                            "Transferred to " + recipientId
                    )
            );

            System.out.println("Transfer successful.");
            System.out.printf("New Balance: ₹%.2f%n",
                    currentAccount.getBalance());

        } catch (NumberFormatException e) {

            System.out.println("Invalid amount.");
        }
    }
}