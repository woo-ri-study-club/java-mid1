package section6.domain;

public enum TransactionType {
    DEPOSIT("입금") {
        @Override
        public void execute(Account account, long amount) {
            verifySingleTransaction(account, amount);
            account.deposit(amount);
        }

        @Override
        public void execute(Account sender, Account receiver, long amount) {
            throw new UnsupportedOperationException("입금은 하나의 계좌만 가능합니다.");
        }
    },
    WITHDRAW("출금") {
        @Override
        public void execute(Account account, long amount) {
            verifySingleTransaction(account, amount);
            verifyBalance(account, amount);
            account.withdraw(amount);
        }

        @Override
        public void execute(Account sender, Account receiver, long amount) {
            throw new UnsupportedOperationException("출금은 하나의 계좌만 가능합니다.");
        }
    },
    TRANSFER("송금") {
        @Override
        public void execute(Account account, long amount) {
            throw new UnsupportedOperationException("송금은 두 계좌가 필요합니다.");
        }

        @Override
        public void execute(Account sender, Account receiver, long amount) {
            verifyCrossTransaction(sender, receiver, amount);
            sender.withdraw(amount);
            receiver.deposit(amount);
        }
    };

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public abstract void execute(Account account, long amount);

    public abstract void execute(Account sender, Account receiver, long amount);

    protected void verifySingleTransaction(Account account, long amount){
        verifyAccountExists(account);
        verifyAccountStatus(account);
        verifyAmount(amount);

    }

    protected void verifyCrossTransaction(Account sender, Account receiver, long amount) {
        verifyAccountExists(sender);
        verifyAccountExists(receiver);
        verifyAccountStatus(sender);
        verifyAccountStatus(receiver);
        verifyAmount(amount);
        verifyBalance(sender, amount);

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

    protected void verifyBalance(Account account, long amount) {
        if (account.getBalance() < amount) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
    }

    protected void verifyAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("1원 이상 거래 가능합니다.");
        }
    }
}
