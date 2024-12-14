package section6.domain;

import java.util.Objects;

public class Account {
    private final Member member;
    private final String accountNumber;
    private long balance;

    public Account(Member member, String accountNumber) {
        this.member = member;
        this.accountNumber = accountNumber;
    }

    public void deposit(long value){
        if(value <= 0){
            throw new IllegalArgumentException("입금액은 0원 이하일 수 없습니다.");
        }

        balance += value;
    }

    public void withdraw(long value){
        if(value <= 0){
            throw new IllegalArgumentException("출금액은 0원 이하일 수 없습니다.");
        }

        if(balance < value){
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        balance -= value;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
