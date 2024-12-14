package section06;

import java.util.Objects;

public class BankAccount {

  private final String accountNumber;

  private final double balance;

  // NOTE: accountNumber는 필수값이며, balance는 0 이상의 값이 들어온다고 가정함 (입력값을 받을 때 체크함)
  public BankAccount(String accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

  public BankAccount deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("0보다 큰 금액을 입금해야 합니다.");
    }
    return new BankAccount(accountNumber, (balance + amount));
  }

  public BankAccount withdraw(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("0보다 큰 금액을 출금해야 합니다.");
    }
    if (amount > balance) {
      throw new IllegalArgumentException("잔액이 부족합니다.");
    }
    return new BankAccount(accountNumber, (balance - amount));
  }

  public double getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    BankAccount that = (BankAccount) o;
    return Objects.equals(accountNumber, that.accountNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(accountNumber);
  }

  @Override
  public String toString() {
    return "BankAccount{" +
        "accountNumber='" + accountNumber + '\'' +
        ", balance=" + balance +
        '}';
  }
}
