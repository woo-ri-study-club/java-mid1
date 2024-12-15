package section6.domain;

public enum TransactionType {
    DEPOSIT("입금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            verifySingleTransaction(sender);
            verifyAmount(amount);
            sender.deposit(amount);
        }
    },
    WITHDRAW("출금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            verifySingleTransaction(sender);
            verifyAmount(amount);
            verifyBalance(sender, amount);
            sender.withdraw(amount);
        }
    },
    TRANSFER("송금") {
        @Override
        public void execute(Account sender, Account receiver, int amount) {
            verifyCrossTransaction(sender, receiver);
            verifyAmount(amount);
            verifyBalance(sender, amount);
            sender.withdraw(amount);
            receiver.deposit(amount);
        }
    };

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public abstract void execute(Account sender, Account receiver, int amount);

    protected void verifySingleTransaction(Account account){
        verifyAccountExists(account);
        verifyAccountStatus(account);
    }

    protected void verifyCrossTransaction(Account sender, Account receiver) {
        verifyAccountExists(sender);
        verifyAccountExists(receiver);
        verifyAccountStatus(sender);
        verifyAccountStatus(receiver);

    }

    protected void verifyAccountExists(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("잘못된 입출금 계좌입니다.");
        }
    }

    protected void verifyAccountStatus(Account account) {
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("휴면처리 되었거나 차단된 계좌는 거래가 불가능합니다.");
        }
    }

    protected void verifyBalance(Account account, int amount) {
        if (account.getBalance() < amount) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
    }

    protected void verifyAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("1원 이상 거래 가능합니다.");
        }
    }
}
