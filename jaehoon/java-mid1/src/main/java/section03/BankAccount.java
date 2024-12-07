package section03;

import java.util.Objects;

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
    if (amount <= 0) {
      throw new IllegalArgumentException("0보다 큰 값을 입금해야 합니다.");
    }
    return new BankAccount(accountNumber, owner, (balance + amount));
  }

  public BankAccount withdraw(double amount) {
    if (balance < amount) {
      throw new IllegalArgumentException("잔액이 부족합니다.");
    }
    return new BankAccount(accountNumber, owner, (balance - amount));
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
        ", owner='" + owner + '\'' +
        ", balance=" + balance +
        '}';
  }
}
