package section6;

public class Account {
    private final Long accountNumber;
    private int balance;

    private Account(long accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static Account createAccount() {
        return new Account(AccountNumberManager.getInstance().generate(), 0);
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 입금할 수 없습니다.");
        }
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 출금할 수 없습니다.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        balance -= amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }
}
