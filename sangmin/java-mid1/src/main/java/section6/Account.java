package section6;

import java.util.concurrent.atomic.AtomicLong;

public class Account {
    private final Long accountNumber;
    private AtomicLong balance;

    private Account(long accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = new AtomicLong(balance);
    }

    public static Account createAccount() {
        return new Account(AccountNumberManager.getInstance().generate(), 0);
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 입금할 수 없습니다.");
        }
        balance.addAndGet(+amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 출금할 수 없습니다.");
        }
        if (balance.get() < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        balance.getAndAdd(-amount);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance.get();
    }
}
