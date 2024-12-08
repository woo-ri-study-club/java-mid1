package src.section3;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        if(balance < 0) {
            throw new IllegalArgumentException("잘못된 계좌 금액입니다. (음수)");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("잘못된 입금 금액입니다. (음수)");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("잘못된 출금 금액입니다. (음수)");
        }
        double newBalance = balance - amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new BankAccount(accountNumber, owner, newBalance);
    }

    public double getBalance() {
        return balance;
    }
}
