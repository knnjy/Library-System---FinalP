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
        for (Transaction t : transactions) {
            t.showTransaction();
        }
    }

    public List<Transaction> getTransactionsByUser(User user) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getClient().equals(user)) {
                result.add(t);
            }
        }
        return result;
    }
}
