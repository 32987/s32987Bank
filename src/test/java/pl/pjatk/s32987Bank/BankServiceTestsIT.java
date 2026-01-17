package pl.pjatk.s32987Bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankServiceTestsIT {
    @MockitoBean
    UserStorage userStorage;
    @MockitoBean
    TransactionStorage transactionStorage;
    @Autowired
    BankService bankService;

    @Test
    void doesUserExistTestTrue() {
        when(userStorage.doesUserExist(anyInt())).thenReturn(true);

        boolean b = bankService.doesUserExist(1);

        assertThat(b).isTrue();
    }

    @Test
    void doesUserExistTestFalse() {
        when(userStorage.doesUserExist(anyInt())).thenReturn(false);

        boolean b = bankService.doesUserExist(1);

        assertThat(b).isFalse();
    }
}