package section6.service;

import section6.domain.Account;
import section6.domain.Process;

import java.util.HashMap;
import java.util.Map;

public class ATMService {
    private final Map<String, Account> accounts = new HashMap<>();

    public void registerAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("등록할 계좌가 비어있습니다.");
        }

        if (accounts.containsKey(account)) {
            throw new IllegalArgumentException("이미 등록된 계좌입니다.");
        }

        accounts.put(account.getAccountNumber(), account);
    }

    public void deposit(String accountNumber, long value) {
        Account account = findAccount(accountNumber);

        account.deposit(value);
        printLog(Process.DEPOSIT, accountNumber, account.getBalance());
    }

    public void withdraw(String accountNumber, long value) {
        Account account = findAccount(accountNumber);

        account.withdraw(value);
        printLog(Process.WITHDRAW, accountNumber, account.getBalance());
    }

    public void transfer(String from, String to, long value) {
        Account fromAccount = findAccount(from);
        Account toAccount = findAccount(to);

        fromAccount.withdraw(value);
        printLog(Process.TRANSFER, from, fromAccount.getBalance());

        toAccount.deposit(value);
        printLog(Process.TRANSFER, to, toAccount.getBalance());
    }

    private Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException(accountNumber + " - 해당 계좌를 찾을 수 없습니다.");
        }

        return account;
    }

    private void printLog(Process process, String accountNumber, long value) {
        System.out.printf("[%s] 계좌번호: %s, 잔액: %d원\n", process, accountNumber, value);
    }


}
