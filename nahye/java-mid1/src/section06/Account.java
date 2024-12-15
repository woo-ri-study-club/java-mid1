package section06;

public class Account {

    private String accountNumber;
    private Customer owner;

    private long balance;

    public Account(String accountNumber, Customer owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("유효하지 않은 입금액입니다.");
        }
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("유효하지 않은 출금액입니다.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 모자랍니다.");
        }
        balance -= amount;
    }

    public void transfer(long amount, Account targetAccount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("유효하지 않은 송금액입니다.");
        }
        if (targetAccount == null) {
            throw new IllegalArgumentException("송금할 계좌가 존재하지 않습니다.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("잔액이 모자랍니다.");
        }
        balance -= amount;
        targetAccount.balance += amount;
    }

    @Override
    public String toString() {
        return owner.getName()+"님의 잔액은 "+balance+"입니다.";
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
