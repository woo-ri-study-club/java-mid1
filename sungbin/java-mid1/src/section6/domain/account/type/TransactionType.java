package section6.domain.account.type;

import section6.global.type.EnumType;

public enum TransactionType implements EnumType {
    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("송금");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
