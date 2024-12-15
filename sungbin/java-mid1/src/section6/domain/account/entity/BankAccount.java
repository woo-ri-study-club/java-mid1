package section6.domain.account.entity;

import section6.domain.member.entity.Member;
import section6.global.exception.BankAccountException;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {

    private final Member accountHolder;

    private AtomicLong balance;

    public BankAccount(Member accountHolder, long balance) {
        this.accountHolder = accountHolder;
        this.balance = new AtomicLong(balance);
    }

    public Member getAccountHolder() {
        return accountHolder;
    }

    public long getBalance() {
        return balance.get();
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new BankAccountException("입금 금액이 올바르지 않습니다.");
        }

        balance.addAndGet(amount);
    }

    public void withdraw(long amount) {
        if (amount <= 0) {
            throw new BankAccountException("출금 금액이 올바르지 않습니다.");
        }

        if (amount > balance.get()) {
            throw new BankAccountException("잔액이 부족합니다.");
        }

        balance.addAndGet(-amount);
    }

    public void transferTo(BankAccount targetAccount, long amount) {
        if (targetAccount == null) {
            throw new BankAccountException("대상 계좌가 존재하지 않습니다.");
        }

        this.withdraw(amount);
        targetAccount.deposit(amount);
    }

    @Override
    public String toString() {
        return accountHolder.toString() + " 계좌 잔액: " + balance;
    }
}
