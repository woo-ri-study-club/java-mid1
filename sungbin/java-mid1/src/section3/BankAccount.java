package section3;

import java.util.Objects;

public class BankAccount {

    private final String accountNumber;

    private final Member owner;

    private final long balance;

    public BankAccount(String accountNumber, Member owner, long balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("잔액이 잘못되었습니다.");
        }

        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금할 금액이 잘못 되었습니다.");
        }

        return this.withBalance(this.balance + amount);
    }

    public BankAccount withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금할 금액이 잘못 되었습니다.");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        return this.withBalance(this.balance - amount);
    }

    public Member getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(getOwner(), that.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return String.format("[계좌번호: %s, 소유자: %s, 잔액: %d]", this.accountNumber, this.owner, this.balance);
    }

    private BankAccount withBalance(long balance) {
        return new BankAccount(this.accountNumber, this.owner, balance);
    }
}
