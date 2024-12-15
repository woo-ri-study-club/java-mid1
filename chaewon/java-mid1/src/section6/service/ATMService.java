package section6.service;

import section6.domain.Account;
import section6.domain.Process;

import java.util.HashMap;
import java.util.Map;

public class ATMService {
    private final Map<String, Account> accounts = new HashMap<>();

    public void registerAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("계좌가");
        }

        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("이미 등록된 계좌입니다.");
        }

        accounts.put(account.getAccountNumber(), account);
    }

    public void deposit(String accountNumber, long value) {
        Account account = findAccount(accountNumber);

        account.deposit(value);
        printLog(account, Process.DEPOSIT);
    }

    public void withdraw(String accountNumber, long value) {
        Account account = findAccount(accountNumber);

        account.withdraw(value);
        printLog(account, Process.WITHDRAW);
    }

    public void transfer(String from, String to, long value) {
        Account fromAccount = findAccount(from);
        Account toAccount = findAccount(to);

        fromAccount.withdraw(value);
        printLog(fromAccount, Process.TRANSFER);

        toAccount.deposit(value);
        printLog(toAccount, Process.TRANSFER);
    }

    public void abstractAccout(String accountNumber){
        accounts.remove(accountNumber);
    }

    private Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException(accountNumber + " - 해당 계좌를 찾을 수 없습니다.");
        }

        return account;
    }

    private void printLog(Account account, Process process) {
        System.out.println(account.serviceLog(process, account.getAccountNumber(), account.getBalance()));
    }

}
