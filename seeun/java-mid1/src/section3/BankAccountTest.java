package section3;

public class BankAccountTest {

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("123-456", "홍길동", 1000.0);
        BankAccount account2 = account1.deposit(500.0);
        BankAccount account3 = account2.withdraw(200.0);
//        BankAccount account4 = account2.withdraw(-200.0); // 에러 발생 : 0원 이하는 출금할 수 없습니다.
//        BankAccount account5 = new BankAccount("234-678", "김길동", -500); // 에러 발생 : 계좌 개설 시 잔액은 0원 이상이어야 합니다.

        System.out.println(account1.getBalance()); // 1000.0
        System.out.println(account2.getBalance()); // 1500.0
        System.out.println(account3.getBalance()); // 1300.0
    }
}
