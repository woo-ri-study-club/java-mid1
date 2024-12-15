package src.section6;

public enum TransferStatus {
    PENDING("처리중"),
    COMPLETED("송금 완료"),
    FAILED("송금 실패");

    private final String description;

    TransferStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}