package section3;

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
            throw new IllegalArgumentException("음수 금액을 입금할 수 없습니다.");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음수 금액을 출금할 수 없습니다.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        return new BankAccount(accountNumber, owner, balance - amount);
    }

    public double getBalance() {
        return balance;
    }
}
