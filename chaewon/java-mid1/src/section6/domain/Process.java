package section6.domain;

public enum Process {
    DEPOSIT("입금"),
    WITHDRAW("출금"),
    TRANSFER("송금");

    private final String desc;

    Process(String desc) {
        this.desc = desc;
    }
}
