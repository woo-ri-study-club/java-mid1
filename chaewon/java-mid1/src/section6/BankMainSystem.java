package section6;

import section6.domain.Account;
import section6.domain.Member;
import section6.service.ATMService;

public class BankMainSystem {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();

        Member memberA = new Member("001", "홍길동");
        Member memberB = new Member("002", "새이름");

        Account accountA = new Account(memberA, "110-111");
        Account accountB = new Account(memberB, "110-112");

        atmService.registerAccount(accountA);
        atmService.registerAccount(accountB);

        atmService.deposit("110-111", 1000);
        atmService.deposit("110-112", 500);

        atmService.withdraw("110-111", 500);
        atmService.withdraw("110-112", 500);

        atmService.transfer("110-111", "110-112", 500);

    }
}
