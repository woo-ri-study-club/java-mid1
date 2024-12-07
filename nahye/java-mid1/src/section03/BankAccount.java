package section03;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야합니다.");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if(balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new BankAccount(accountNumber, owner, balance - amount);
    }

    public double getBalance() {
        return balance;
    }
}
