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
}