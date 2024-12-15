package section6;

import section6.transaction.Deposit;
import section6.transaction.Transfer;
import section6.transaction.Withdraw;

public enum TransactionType {
    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("이체"),
    ;

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static Transaction handler(TransactionType transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                return new Deposit();
            case WITHDRAW:
                return new Withdraw();
            case TRANSFER:
                return new Transfer();
            default:
                throw new IllegalArgumentException("지원하지 않는 거래 유형입니다.");
        }
    }
}
