package pl.pjatk.s32987Bank;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserStorage {
    private List<User> users = new ArrayList<>();

    UserStorage() {

    }

    public User getUserById(int userId) {
        return users.stream().filter(u -> u.getId() == userId).toList().getFirst();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public BigDecimal getUserBalanceById(int userId) {
        return getUserById(userId).getBalance();
    }

    public void addToUserBalance(int userId, BigDecimal amount) {
        getUserById(userId).setBalance(getUserById(userId).getBalance().add(amount));
    }

    public void subtractFromUserBalance(int userId, BigDecimal amount) {
        getUserById(userId).setBalance(getUserById(userId).getBalance().subtract(amount));
    }

    public boolean doesUserExist(int userId) {
        return !users.stream().filter(u -> u.getId() == userId).toList().isEmpty();
    }

    public List<User> getUsers() {
        return users;
    }

    public int getNextUserId() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserStorage{" +
                "users=" + users +
                '}';
    }
}
