package section6.domain;

public enum TransactionType {
    DEPOSIT("입금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            if(sender == null || receiver == null){
                throw new IllegalArgumentException("잘못된 입출금 계좌입니다.");
            }
            if (sender.getStatus() != AccountStatus.ACTIVE) {
                throw new IllegalStateException("휴면처리 되었거나 차단된 계좌입니다.");
            }
            if(amount <= 0){
                throw new IllegalArgumentException("1원 이상 입금가능합니다.");
            }
            sender.deposit(amount);
        }
    },
    WITHDRAW("출금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            if(sender == null || receiver == null){
                throw new IllegalArgumentException("잘못된 입출금 계좌입니다.");
            }
            if (sender.getStatus() != AccountStatus.ACTIVE) {
                throw new IllegalStateException("휴면처리 되었거나 차단된 계좌입니다.");
            }
            if(amount <= 0){
                throw new IllegalArgumentException("1원 이상 출금가능합니다.");
            }
            if(sender.getBalance() < amount){
                throw new IllegalStateException("잔액이 부족합니다.");
            }

            sender.withdraw(amount);
        }
    },
    TRANSFER("송금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            if(sender == null || receiver == null){
                throw new IllegalArgumentException("잘못된 입출금 계좌입니다.");
            }
            if (sender.getStatus() != AccountStatus.ACTIVE) {
                throw new IllegalStateException("휴면처리 되었거나 차단된 계좌에서 송금은 불가합니다.");
            }
            if (receiver.getStatus() != AccountStatus.ACTIVE) {
                throw new IllegalStateException("휴면처리 되었거나 차단된 계좌로의 송금은 불가합니다.");
            }
            if(amount <= 0){
                throw new IllegalArgumentException("1원 이상 송금가능합니다.");
            }
            if (sender.getBalance() < amount) {
                throw new IllegalStateException("잔액이 부족합니다.");
            }

            sender.withdraw(amount);
            receiver.deposit(amount);
        }
    };

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public abstract void execute(Account sender, Account receiver, int amount);

}
