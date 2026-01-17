package pl.pjatk.s32987Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserStorageTests {
    private UserStorage userStorage;

    @BeforeEach
    void initializeUserStorage() {
        userStorage = new UserStorage();
    }

    @Test
    void getUserByIdTest() {
        //given
        int userId = 1;
        User user = new User(userId, BigDecimal.valueOf(10));

        //when
        userStorage.addUser(user);

        //then
        assertEquals(user, userStorage.getUserById(userId));
    }

    @Test
    void addUserTest() {
        //given
        User user = new User(0, BigDecimal.valueOf(10));

        //when
        userStorage.addUser(user);

        //then
        assertTrue(userStorage.getUsers().contains(user));
    }

    @Test
    void getUserBalanceByIdTest() {
        //given
        int userId = 0;
        User user = new User(userId, BigDecimal.valueOf(10));
        userStorage.addUser(user);

        //when
        BigDecimal balance = userStorage.getUserBalanceById(userId);

        //then
        assertEquals(BigDecimal.valueOf(10), balance);
    }

    @Test
    void addToUserBalanceTest() {
        //given
        User user = new User(0, BigDecimal.valueOf(10));
        userStorage.addUser(user);

        //when
        userStorage.addToUserBalance(0, BigDecimal.valueOf(50));

        //then
        assertEquals(BigDecimal.valueOf(60), userStorage.getUserById(0).getBalance());
    }

    @Test
    void subtractFromUserBalanceTest() {
        //given
        User user = new User(0, BigDecimal.valueOf(10));
        userStorage.addUser(user);

        //when
        userStorage.subtractFromUserBalance(0, BigDecimal.valueOf(8));

        //then
        assertEquals(BigDecimal.valueOf(2), userStorage.getUserById(0).getBalance());
    }

    @Test
    void doesUserExistTrueTest() {
        //given
        int userId = 0;
        User user = new User(userId, BigDecimal.valueOf(10));
        userStorage.addUser(user);

        //when
        boolean doesUserExist = userStorage.doesUserExist(userId);

        //then
        assertTrue(doesUserExist);
    }

    @Test
    void doesUserExistFalseTest() {
        //given
        int userId = 0;
        User user = new User(userId, BigDecimal.valueOf(10));

        //when
        boolean doesUserExist = userStorage.doesUserExist(userId);

        //then
        assertFalse(doesUserExist);
    }
}
