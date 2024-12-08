public class BankAccount {
    private final String accountNumber;
    private final String owner;
    private final double balance;

    public BankAccount(String accountNumber, String owner, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("0원 보다 더 작은 금액은 입력할 수 없습니다.");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public BankAccount withDeposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("입금하실 금액을 다시 입력해주세요");
        }
        return new BankAccount(accountNumber, owner, balance + amount);
    }

    public BankAccount withWithdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("출금할 금액을 다시 입력해주세요.");
        }
        double newBalance = balance - amount;

        if (newBalance < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
        return new BankAccount(accountNumber, owner, newBalance);
    }

}
