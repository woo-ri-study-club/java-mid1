package section03;

import java.util.Objects;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public BankAccount deposit(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야합니다.");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야합니다.");
        }
        if(balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new BankAccount(accountNumber, owner, balance - amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }
}
