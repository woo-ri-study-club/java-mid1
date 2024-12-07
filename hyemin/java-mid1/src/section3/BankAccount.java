package src.section3;

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
        if (amount < 0) {
            throw new IllegalArgumentException("잘못된 금액입니다. (음수)");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        double price = balance - amount;
        if (price < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new BankAccount(accountNumber, owner, price);
    }

    public double getBalance() {
        return balance;
    }
}
