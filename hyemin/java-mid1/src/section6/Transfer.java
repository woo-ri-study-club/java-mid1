package src.section6;

import java.math.BigDecimal;

public class Transfer {
    private final BankAccount sender;
    private final BankAccount receiver;
    private final BigDecimal amount;
    private TransferStatus status;

    public Transfer(BankAccount sender, BankAccount receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.status = TransferStatus.PENDING;
    }

    public void execute() {
        if (processTransfer()) {
            status = TransferStatus.COMPLETED;
        } else {
            status = TransferStatus.FAILED;
        }
        printResult();
    }

    private boolean processTransfer() {
        try {
            sender.withdraw(amount);
            receiver.deposit(amount);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("송금 실패: " + e.getMessage());
            return false;
        }
    }

    private void printResult() {
        if (status == TransferStatus.COMPLETED) {
            System.out.println("송금이 완료되었습니다: " + amount + "원");
        }
    }

    public TransferStatus getStatus() {
        return status;
    }
}
