package pl.pjatk.s32987Bank;

import java.math.BigDecimal;

public class User {
    private int id;
    private BigDecimal balance;

    User(int id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
