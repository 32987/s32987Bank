package pl.pjatk.s32987Bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionStorage {
    private List<Transaction> transactions = new ArrayList<>();

    TransactionStorage() {

    }

    public void addTransaction(Transaction newTransaction) {
        transactions.add(newTransaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getNextId() {
        return transactions.size();
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionStorage{" +
                "transactions=" + transactions +
                '}';
    }
}
