package section3;

public class MissionMain {
    public static void main(String[] args) {
        BankAccount user1 = new BankAccount("001", "홍길동", 0);
        BankAccount user2 = new BankAccount("002", "사용자", 5000);
        System.out.println(user1);
        System.out.println(user2);

        //입금 테스트(정상 입금)
        user1 = user1.deposit(100);
        System.out.println(user1); // 0 -> 100

        //입금 테스트(비정상 입금)
//        user2 = user2.deposit(0); //올바르지 않은 입금액

        //출금 테스트(정상 출금)
        user2 = user2.withdraw(5000);
        System.out.println(user2); // 5000 -> 0

        //출금 테스트(비정상 출금 - 0이하의 출금)
//        user1 = user1.withdraw(0); //올바르지 않은 출금액

        //출금 테스트(비정상 출금 - 잔액 부족)
//        user1 = user1.withdraw(101); //잔액 부족
    }
}
