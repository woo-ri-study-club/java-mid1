package section3;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final long balance;

    public BankAccount(String accountNumber, String owner, long balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("계좌 생성 시 잔액은 0원 이상이어야 합니다.");
        }

        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public BankAccount deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("올바르지 않은 입금액입니다.");
        }

        long result = balance + amount;
        return new BankAccount(accountNumber, owner, result);
    }

    public BankAccount withdraw(long amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("올바르지 않은 출금액입니다.");
        }

        long result = balance - amount;

        if(result < 0){
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        return new BankAccount(accountNumber, owner, result);
    }

    @Override
    public String toString() {
        return owner + "(님)의 잔액: " + balance + "원";
    }
}
