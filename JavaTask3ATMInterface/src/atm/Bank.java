package atm;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        // Sample accounts
        accounts.put("USER1001",
                new Account("USER1001", "1234", 10000));

        accounts.put("USER1002",
                new Account("USER1002", "5678", 5000));

        accounts.put("USER1003",
                new Account("USER1003", "1111", 7500));
    }

    public Account findAccount(String userId) {
        return accounts.get(userId);
    }

    public boolean accountExists(String userId) {
        return accounts.containsKey(userId);
    }
}