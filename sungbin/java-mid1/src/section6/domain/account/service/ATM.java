package section6.domain.account.service;

import section6.domain.account.entity.BankAccount;
import section6.domain.account.type.TransactionType;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ATM {

    private final ConcurrentHashMap<String, BankAccount> accounts = new ConcurrentHashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountHolder().id(), account);
    }

    public void processTransaction(TransactionType type, String sourceId, String targetId, long amount) {
        BankAccount source = accounts.get(sourceId);
        BankAccount target = targetId != null ? accounts.get(targetId) : null;

        switch (type) {
            case DEPOSIT:
                source.deposit(amount);
                System.out.println(source.getAccountHolder().name() + " (" + source.getAccountHolder().id() + ") 님의 계좌에 " + amount + " 입금되었습니다.");
                break;
            case WITHDRAW:
                source.withdraw(amount);
                System.out.println(source.getAccountHolder().name() + " (" + source.getAccountHolder().id() + ") 님의 계좌에서 " + amount + " 출금되었습니다.");
                break;
            case TRANSFER:
                source.transferTo(target, amount);
                System.out.println(source.getAccountHolder().name() + " (" + source.getAccountHolder().id() + ") 님이 "
                        + Objects.requireNonNull(target).getAccountHolder().name() + " (" + target.getAccountHolder().id() + ") 님에게 " + amount + " 송금하였습니다.");
                break;
            default:
                throw new UnsupportedOperationException("지원되지 않는 거래 유형입니다.");
        }
    }
}
