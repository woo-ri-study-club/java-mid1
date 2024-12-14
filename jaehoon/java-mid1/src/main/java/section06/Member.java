package section06;

import static java.util.Objects.isNull;

public class Member {

  private String name;

  private BankAccount bankAccount;

  public Member(String name, BankAccount bankAccount) {
    this.name = name;
    this.bankAccount = bankAccount;
  }

  public String getName() {
    return name;
  }

  public void updateBankAccount(BankAccount bankAccount) {
    if (isNull(bankAccount)) {
      throw new IllegalArgumentException("유효하지 않은 계좌입니다.");
    }
    this.bankAccount = bankAccount;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }
}
