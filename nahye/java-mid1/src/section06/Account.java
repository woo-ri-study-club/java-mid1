package section06;

public class Account {

    private int accountNumber;
    private Customer owner;

    private int balance;

    public Account(int accountNumber, Customer owner, int balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야합니다.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 모자랍니다.");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야합니다.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 모자랍니다.");
        }
        balance -= amount;
    }

    public void transfer(int amount, Account targetAccount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("송금액은 0보다 커야합니다.");
        }
        if (targetAccount == null) {
            throw new IllegalArgumentException("송금할 계좌가 존재하지 않습니다.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("잔액이 모자랍니다.");
        }
        balance -= amount;
        targetAccount.balance += balance;
    }

    @Override
    public String toString() {
        return owner.getName()+"님의 잔액은 "+balance+"입니다.";
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
