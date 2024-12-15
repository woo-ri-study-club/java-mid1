package section6.domain;

public class Account {
    private final String accountNumber;
    private double balance;
    private AccountStatus status;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    public void executeTransaction(TransactionType type, Account receiver, int amount) {
        type.execute(this, receiver, amount);
    }

    public void changeStatus(AccountStatus accountStatus) {
        status = accountStatus;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public double getBalance() {
        return balance;
    }
}