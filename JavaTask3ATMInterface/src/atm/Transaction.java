package atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private double balanceAfter;
    private String details;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount,
                       double balanceAfter, String details) {

        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.details = details;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return String.format(
                "%-12s | Amount: ₹%-10.2f | Balance: ₹%-10.2f | %s | %s",
                type,
                amount,
                balanceAfter,
                details,
                dateTime.format(formatter)
        );
    }
}