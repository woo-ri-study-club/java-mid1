package section06;

public class TransactionResult {

  private final TransactionType transactionType;

  private final double amount;

  private final Member member;

  private Member targetMember; // 송금의 경우에만 사용

  public TransactionResult(TransactionType transactionType, double amount, Member member) {
    this.transactionType = transactionType;
    this.member = member;
    this.amount = amount;
  }

  public TransactionResult(TransactionType transactionType, double amount, Member member, Member targetMember) {
    this(transactionType, amount, member);
    this.targetMember = targetMember;
  }

  public String getTransactionSummary() {
    return switch (transactionType) {
      case DEPOSIT -> String.format("%s님의 계좌에 `%.0f원` 입금되었습니다.", member.getName(), amount);
      case WITHDRAW -> String.format("%s님의 계좌에서 `%.0f원` 출금되었습니다.", member.getName(), amount);
      case TRANSFER -> String.format("%s님이 %s님에게 `%.0f원` 송금하였습니다.", member.getName(), targetMember.getName(), amount);
    };
  }
}