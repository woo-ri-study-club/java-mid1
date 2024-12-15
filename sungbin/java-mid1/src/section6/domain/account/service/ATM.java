package section6.domain.account.service;

import section6.domain.account.entity.BankAccount;
import section6.domain.account.type.TransactionType;
import section6.global.common.log.TransactionLogger;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ATM {

    private final ConcurrentHashMap<String, BankAccount> accounts = new ConcurrentHashMap<>();

    private final TransactionLogger logger;

    public ATM(TransactionLogger logger) {
        this.logger = logger;
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountHolder().id(), account);
    }

    public void processTransaction(TransactionType type, String sourceId, String targetId, long amount) {
        BankAccount source = accounts.get(sourceId);

        switch (type) {
            case DEPOSIT:
                source.deposit(amount);
                logger.logDeposit(source, amount);
                break;
            case WITHDRAW:
                source.withdraw(amount);
                logger.logWithdraw(source, amount);
                break;
            case TRANSFER:
                BankAccount target = Objects.requireNonNull(accounts.get(targetId), "송금 대상 계좌가 존재하지 않습니다.");
                source.transferTo(target, amount);
                logger.logTransfer(source, target, amount);
                break;
            default:
                throw new UnsupportedOperationException("지원되지 않는 거래 유형입니다.");
        }
    }
}
