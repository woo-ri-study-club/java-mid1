package src.section6;

public class Transfer {
    private final BankAccount sender;
    private final BankAccount receiver;
    private final double amount;
    private TransferStatus status;

    public Transfer(BankAccount sender, BankAccount receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = TransferStatus.PENDING;
    }

    public void execute() {
        try {
            sender.withdraw(amount);
            receiver.deposit(amount);
            status = TransferStatus.COMPLETED;
            System.out.println("송금이 완료되었습니다: " + amount + "원");
        } catch (IllegalArgumentException e) {
            status = TransferStatus.FAILED;
            System.out.println("송금 실패: " + e.getMessage());
        }
    }

    public TransferStatus getStatus() {
        return status;
    }
}
