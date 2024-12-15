package section6;

import section6.domain.Account;
import section6.domain.AccountStatus;
import section6.domain.TransactionType;

public class AccountTest {

    public static void main(String[] args) {

        Account account1 = new Account("123-456", 10000);
        Account account2 = new Account("234-567", 20000);

//        잔액보다 많은 금액 출금, "잔액이 부족합니다." 에러 발생
//        account1.executeTransaction(TransactionType.WITHDRAW, account1, 15000);
//        System.out.println("account1 15000원 출금 후 계좌 잔액");
//        System.out.println("account1.getBalance() = " + account1.getBalance());


//        account1.changeStatus(AccountStatus.DORMANT);
//        활성화 상태가 아닌 계좌에 입금, "휴면처리 되었거나 차단된 계좌입니다." 에러 발생
//        account1.executeTransaction(TransactionType.DEPOSIT, account1, 5000);

        account1.executeTransaction(TransactionType.DEPOSIT, account1, 5000);
        System.out.println("account1 5000원 입금 후 계좌 잔액");
        System.out.println("account1.getBalance() = " + account1.getBalance());


//        계좌 잔액보다 많은 금액을 송금 시도, "잔액이 부족합니다." 에러 발생
//        account1.executeTransaction(TransactionType.TRANSFER, account2, 30000);

//        잔액 이하 금액 송금 시도
        account1.executeTransaction(TransactionType.TRANSFER, account2, 15000);
        System.out.println("account1에서 account2로 15000원 송금 후 두 계좌 잔액");
        System.out.println("account1.getBalance() = " + account1.getBalance());
        System.out.println("account2.getBalance() = " + account2.getBalance());


    }
}
