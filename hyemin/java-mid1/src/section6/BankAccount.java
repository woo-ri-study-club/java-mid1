package src.section6;

import java.math.BigDecimal;

public class BankAccount {
    private final String accountNumber;
    private BigDecimal balance;

    public BankAccount(String accountNumber, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
        } else {
            throw new IllegalArgumentException("입금할 금액이 유효하지 않습니다.");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
