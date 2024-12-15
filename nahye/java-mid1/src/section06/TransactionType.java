package section06;

public enum TransactionType {
    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("송금"),
    BALANCE("조회"),
    EXIT("종료");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TransactionType findByDescription(String description) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("올바르지 않은 거래 종류입니다: " + description);
    }
}





