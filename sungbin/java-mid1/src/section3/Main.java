package section3;

public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("123-456", "홍길동", 100000);
        BankAccount account2 = account1.deposit(50000);
        BankAccount account3 = account2.withdraw(20000);

        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
    }
}
