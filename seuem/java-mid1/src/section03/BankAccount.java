public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("음수 금액을 입력할 수 없습니다.");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public BankAccount deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음수 금액은 입금할 수 없습니다");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("입금하실 금액을 다시 입력해주세요");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금할 금액을 다시 입력해주세요.");
        }
        double newBalance = balance - amount;

        if (newBalance < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
        return new BankAccount(accountNumber, owner, newBalance);
    }

    @Override
    public String toString() {
        return "BankAccount " + accountNumber + ": " + owner + " balance:" + balance;
    }
}
