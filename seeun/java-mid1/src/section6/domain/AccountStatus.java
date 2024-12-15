package section6.domain;

public enum AccountStatus {
    ACTIVE("활동"),
    BLOCKED("차단"),
    DORMANT("휴면");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
