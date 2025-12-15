package model;

import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    private List<Transaction> transactions;

    public TransactionLog() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void showAllTransactions() {
        System.out.println("=== TRANSACTION HISTORY ===");
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (Transaction t : transactions) {
                t.showTransaction();
            }
        }
    }

    // Show transactions for a specific user
    public void showTransactionsByUser(User user) {
        System.out.println("=== Transactions for " + user.getName() + " ===");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getClient().equals(user)) {
                t.showTransaction();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found.");
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

}
