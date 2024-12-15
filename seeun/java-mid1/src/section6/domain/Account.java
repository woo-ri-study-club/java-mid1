package section6.domain;

public class Account {
    private final String accountNumber;
    private long balance;
    private AccountStatus status;

    public Account(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    public void executeTransaction(TransactionType type, Account receiver, long amount) {
        type.execute(this, receiver, amount);
    }

    public void changeStatus(AccountStatus accountStatus) {
        status = accountStatus;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public long getBalance() {
        return balance;
    }
}