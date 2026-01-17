package pl.pjatk.s32987Bank;

import java.math.BigDecimal;

public class Transaction {
    public enum Status {
        ACCEPTED,
        DECLINED
    }
    private int id;
    private int userId;
    private Status status;
    private BigDecimal balanceAfterTransaction;

    public Transaction(int id, int userId, Status status, BigDecimal balanceAfterTransaction) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                '}';
    }
}
