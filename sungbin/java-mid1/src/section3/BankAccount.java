package section3;

import java.util.Objects;

public class BankAccount {

    private final String accountNumber;

    private final String owner;

    private final long balance;

    public BankAccount(String accountNumber, String owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금할 금액이 잘못 되었습니다.");
        }

        return new BankAccount(this.accountNumber, this.owner, this.balance + amount);
    }

    public BankAccount withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금할 금액이 잘못 되었습니다.");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        return new BankAccount(this.accountNumber, this.owner, this.balance - amount);
    }

    public long getBalance() {
        return balance;
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

    @Override
    public String toString() {
        return String.format("[계좌번: %s, 소유자: %s, 잔액: %d]", this.accountNumber, this.owner, this.balance);
    }
}
