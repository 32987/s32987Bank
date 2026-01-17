package pl.pjatk.s32987Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankServiceUnitTests {
    BankService bankService;
    TransactionStorage transactionStorage;
    UserStorage userStorage;
    @BeforeEach
    void initialize() {
        userStorage = new UserStorage();
        transactionStorage = new TransactionStorage();
        bankService = new BankService(userStorage, transactionStorage);
    }

    @Test
    void displayUserDataByIdTestTrue() {
        //given
        int userId = 1;
        userStorage.addUser(new User(userId, BigDecimal.valueOf(10)));

        //when
        boolean b = bankService.displayUserDataById(userId);

        //then
        assertTrue(b);

    }

    @Test
    void displayUserDataByIdTestFalse() {
        //given
        int userId = 1;

        //when
        boolean b = bankService.displayUserDataById(userId);

        //then
        assertFalse(b);

    }

    @Test
    void registerUserTest() {
        //given
        BigDecimal balance = BigDecimal.valueOf(20);

        //when
        bankService.registerUser(balance);

        //then
        assertEquals(userStorage.getUsers().getFirst().getBalance(), balance);
    }

    @Test
    void createTransferTest() {
        //given
        int userId = 0;
        BigDecimal balance = BigDecimal.valueOf(20);
        User user = new User(userId, balance);
        userStorage.addUser(user);

        //when
        bankService.createTransfer(userId, BigDecimal.valueOf(20));

        //then
        assertEquals(BigDecimal.valueOf(0), userStorage.getUserById(userId).getBalance());
    }

    @Test
    void addToUserBalanceTest() {
        //given
        int userId = 0;
        BigDecimal balance = BigDecimal.valueOf(20);
        User user = new User(userId, balance);
        userStorage.addUser(user);

        //when
        bankService.addToUserBalance(userId, BigDecimal.valueOf(20));

        //then
        assertEquals(BigDecimal.valueOf(40), userStorage.getUserById(userId).getBalance());
    }
}
