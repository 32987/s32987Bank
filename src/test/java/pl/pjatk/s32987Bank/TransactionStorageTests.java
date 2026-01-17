package pl.pjatk.s32987Bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionStorageTests {
    private TransactionStorage transactionStorage;

    @BeforeEach
    void initializeTransactionStorage() {
        transactionStorage = new TransactionStorage();
    }

    @Test
    void addTransactionTest() {
        //given
        Transaction transaction = new Transaction(1, 1, Transaction.Status.ACCEPTED, BigDecimal.valueOf(10));

        //when
        transactionStorage.addTransaction(transaction);

        //then
        assertTrue(transactionStorage.getTransactions().contains(transaction));
    }
}
