package section6.global.common.log;

import section6.domain.account.entity.BankAccount;

public interface TransactionLogger {
    void logDeposit(BankAccount account, long amount);

    void logWithdraw(BankAccount account, long amount);

    void logTransfer(BankAccount source, BankAccount target, long amount);
}
