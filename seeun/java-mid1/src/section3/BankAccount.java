package section3;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        if(balance < 0){
            throw new IllegalArgumentException("계좌 개설 시 잔액은 0원 이상이어야 합니다.");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(double amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("0원 이하는 입금할 수 없습니다. ");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("0원 이하는 출금할 수 없습니다. ");
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
