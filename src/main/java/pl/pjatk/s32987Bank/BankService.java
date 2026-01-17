package pl.pjatk.s32987Bank;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankService {
    private final UserStorage userStorage;
    private final TransactionStorage transactionStorage;

    BankService(UserStorage userStorage, TransactionStorage transactionStorage) {
        this.userStorage = userStorage;
        this.transactionStorage = transactionStorage;
    }

    public boolean displayUserDataById(int userId) {
        if (doesUserExist(userId)) {
            System.out.println("Uzytkownik: " + userStorage.getUserById(userId));
            return true;
        } else {
            System.out.println("Podany uzytkownik nie istnieje.");
            return false;
        }
    }

    public void displayUsers() {
        userStorage.getUsers().forEach((u) -> {
            System.out.println("Uzytkownik: " + u);
        });
    }

    public void registerUser(BigDecimal balance) {
        User newUser = new User(userStorage.getNextUserId(), balance);
        userStorage.addUser(newUser);
        System.out.println("Uzytkownik zarejestrowany pomyslnie: " + newUser);
    }

    public Transaction createTransfer(int userId, BigDecimal amount) {
        if (doesUserExist(userId)) {
            if (userStorage.getUserBalanceById(userId).compareTo(amount) >= 0)  {
                userStorage.subtractFromUserBalance(userId, amount);
                System.out.println("Przelew utworzono pomyslnie.");

                return addTransactionToStorageAndGet(transactionStorage.getNextId(),
                        userId,
                        Transaction.Status.ACCEPTED,
                        userStorage.getUserBalanceById(userId)
                );
            } else {
                System.out.println("Uzytkownik nie posiada wystarczajacych srodkow.");

                return addTransactionToStorageAndGet(transactionStorage.getNextId(),
                        userId,
                        Transaction.Status.DECLINED,
                        userStorage.getUserBalanceById(userId)
                );
            }
        } else {
            System.out.println("Podany uzytkownik nie istnieje.");

            return addTransactionToStorageAndGet(transactionStorage.getNextId(),
                    userId,
                    Transaction.Status.DECLINED,
                    userStorage.getUserBalanceById(userId)
            );
        }
    }

    public Transaction addToUserBalance(int userId, BigDecimal amount) {
        if (doesUserExist(userId)) {
            userStorage.addToUserBalance(userId, amount);
            System.out.println("Pomyslnie wplacono " + amount.intValue() + " zl na konto o ID " + userId + ".");

            return addTransactionToStorageAndGet(transactionStorage.getNextId(),
                    userId,
                    Transaction.Status.ACCEPTED,
                    userStorage.getUserBalanceById(userId)
            );
        } else {
            System.out.println("Podany uzytkownik nie istnieje.");

            return addTransactionToStorageAndGet(transactionStorage.getNextId(),
                    userId,
                    Transaction.Status.DECLINED,
                    userStorage.getUserBalanceById(userId)
            );
        }
    }

    public Transaction addTransactionToStorageAndGet(int transactionId, int userId, Transaction.Status status, BigDecimal userBalance) {
        Transaction newTransaction = new Transaction(transactionId, userId, status, userBalance);
        transactionStorage.addTransaction(newTransaction);
        return newTransaction;
    }

    public boolean doesUserExist(int userId) {
        return userStorage.doesUserExist(userId);
    }
}