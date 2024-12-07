public class BankAccount {
    final String accountNumber;
    final String owner;
    final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public BankAccount deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음수 금액은 입금할 수 없습니다");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("입금하실 금액을 입력해주세요");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        double newBalance = balance - amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
        return new BankAccount(accountNumber, owner, newBalance);
    }
}
