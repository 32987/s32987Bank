package pl.pjatk.s32987Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class S32987BankApplication {
    private final BankService bankService;

    S32987BankApplication(BankService bankService) {
        this.bankService = bankService;

        bankService.registerUser(BigDecimal.valueOf(10));
        bankService.registerUser(BigDecimal.valueOf(10));
        bankService.registerUser(BigDecimal.valueOf(10));
        bankService.registerUser(BigDecimal.valueOf(10));

        bankService.addToUserBalance(1, BigDecimal.valueOf(20));

        bankService.displayUsers();

        bankService.createTransfer(0, BigDecimal.valueOf(20));
        bankService.createTransfer(0, BigDecimal.valueOf(10));
        bankService.createTransfer(1, BigDecimal.valueOf(9));

        bankService.displayUsers();

        bankService.displayUserDataById(0);

    }

	public static void main(String[] args) {
		SpringApplication.run(S32987BankApplication.class, args);
	}

}
