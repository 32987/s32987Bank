package pl.pjatk.s32987Bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTests {
    @Mock
    UserStorage userStorage;
    @Mock
    TransactionStorage transactionStorage;
    @InjectMocks
    BankService bankService;

    @Test
    void doesUserExistTestTrue() {
        when(userStorage.doesUserExist(anyInt())).thenReturn(true);

        boolean b = bankService.doesUserExist(1);

        assertTrue(b);
    }

    @Test
    void doesUserExistTestFalse() {
        when(userStorage.doesUserExist(anyInt())).thenReturn(false);

        boolean b = bankService.doesUserExist(1);

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
        assertEquals(BigDecimal.valueOf(40), userStorage.getUserById(userId).getBalance());
    }

    @Test
    void addToUserBalanceTest() {
        //given
        int userId = 0;
        BigDecimal balance = BigDecimal.valueOf(20);
        User user = new User(userId, balance);
        userStorage.addUser(user);

        //when
        bankService.createTransfer(userId, BigDecimal.valueOf(20));

        //then
        assertEquals(BigDecimal.valueOf(40), userStorage.getUserById(userId).getBalance());
    }

    @Test
    void addTransactionToStorageAndGetTransactionId() {
        //given
        int transactionId = 1;
        int userId = 1;
        Transaction.Status status = Transaction.Status.ACCEPTED;
        BigDecimal userBalance = BigDecimal.valueOf(20);

        //when
        Transaction transaction = bankService.addTransactionToStorageAndGet(transactionId, userId, status, userBalance);

        //then
        assertEquals(transactionId, transaction.getId());
    }

    @Test
    void addTransactionToStorageAndGetUserId() {
        //given
        int transactionId = 1;
        int userId = 1;
        Transaction.Status status = Transaction.Status.ACCEPTED;
        BigDecimal userBalance = BigDecimal.valueOf(20);

        //when
        Transaction transaction = bankService.addTransactionToStorageAndGet(transactionId, userId, status, userBalance);

        //then
        assertEquals(userId, transaction.getUserId());
    }

    @Test
    void addTransactionToStorageAndGetStatus() {
        //given
        int transactionId = 1;
        int userId = 1;
        Transaction.Status status = Transaction.Status.ACCEPTED;
        BigDecimal userBalance = BigDecimal.valueOf(20);

        //when
        Transaction transaction = bankService.addTransactionToStorageAndGet(transactionId, userId, status, userBalance);

        //then
        assertEquals(status, transaction.getStatus());
    }

    @Test
    void addTransactionToStorageAndGetUserBalance() {
        //given
        int transactionId = 1;
        int userId = 1;
        Transaction.Status status = Transaction.Status.ACCEPTED;
        BigDecimal userBalance = BigDecimal.valueOf(20);

        //when
        Transaction transaction = bankService.addTransactionToStorageAndGet(transactionId, userId, status, userBalance);

        //then
        assertEquals(userBalance, transaction.getBalanceAfterTransaction());
    }
}