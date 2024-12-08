package section03;

import java.util.Objects;

import static java.util.Objects.isNull;

public class BankAccount {

  private final String accountNumber;

  private final String owner;

  private final double balance;

  public BankAccount(String accountNumber, String owner, double balance) {
    if (isNull(accountNumber) || isNull(owner)) {
      throw new IllegalArgumentException("계좌고유식별자 또는 계좌 소유주 이름은 꼭 필요합니다.");
    }
    this.accountNumber = accountNumber;
    this.owner = owner;

    if (balance < 0) {
      throw new IllegalArgumentException("마이너스 계좌는 만들 수 없습니다.");
    }
    this.balance = balance;
  }

  public BankAccount deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("0보다 큰 금액을 입금해야 합니다.");
    }
    return new BankAccount(accountNumber, owner, (balance + amount));
  }

  public BankAccount withdraw(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("0보다 큰 금액을 출금해야 합니다.");
    }
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
