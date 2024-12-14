package section6;

import section6.domain.account.entity.BankAccount;
import section6.domain.account.service.ATM;
import section6.domain.member.entity.Member;

import static section6.domain.account.type.TransactionType.TRANSFER;
import static section6.domain.account.type.TransactionType.WITHDRAW;

public class Main {
    public static void main(String[] args) {
        // 회원 생성
        Member member1 = new Member("M001", "홍길동");
        Member member2 = new Member("M002", "김철수");

        // 계좌 생성
        BankAccount account1 = new BankAccount(member1, 100000);
        BankAccount account2 = new BankAccount(member2, 50000);

        // ATM 생성
        ATM atm = new ATM();
        atm.addAccount(account1);
        atm.addAccount(account2);

        System.out.println("초기 잔액:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        // 스레드 시뮬레이션
        Thread thread1 = new Thread(() -> atm.processTransaction(TRANSFER, "M001", "M002", 30000));
        Thread thread2 = new Thread(() -> atm.processTransaction(WITHDRAW, "M001", null, 50000));

        thread1.start();
        thread2.start();

        // 스레드 종료 대기
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n최종 잔액:");
        System.out.println(account1);
        System.out.println(account2);
    }
}
