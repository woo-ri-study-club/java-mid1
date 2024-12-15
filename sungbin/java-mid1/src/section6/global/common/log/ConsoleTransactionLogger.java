package section6.global.common.log;

import section6.domain.account.entity.BankAccount;

public class ConsoleTransactionLogger implements TransactionLogger {

    @Override
    public void logDeposit(BankAccount account, long amount) {
        System.out.println(account.getAccountHolder().name() + " (" + account.getAccountHolder().id() + ") 님의 계좌에 " + amount + " 입금되었습니다.");
    }

    @Override
    public void logWithdraw(BankAccount account, long amount) {
        System.out.println(account.getAccountHolder().name() + " (" + account.getAccountHolder().id() + ") 님의 계좌에서 " + amount + " 출금되었습니다.");
    }

    @Override
    public void logTransfer(BankAccount source, BankAccount target, long amount) {
        System.out.println(source.getAccountHolder().name() + " (" + source.getAccountHolder().id() + ") 님이 "
                + target.getAccountHolder().name() + " (" + target.getAccountHolder().id() + ") 님에게 " + amount + " 송금하였습니다.");
    }
}
