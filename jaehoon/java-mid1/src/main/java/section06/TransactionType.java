package section06;

public enum TransactionType {
  DEPOSIT("입금"),
  WITHDRAW("출금"),
  TRANSFER("송금");

  private final String operation;

  TransactionType(String operation) {
    this.operation = operation;
  }

  public static TransactionType findByTransactionType(String operation) {
    for (TransactionType transactionType : values()) {
      if (transactionType.getOperation().equals(operation)) {
        return transactionType;
      }
    }
    return null;
  }

  public String getOperation() {
    return operation;
  }
}
