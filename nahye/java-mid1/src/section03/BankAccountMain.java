package section03;

public class BankAccountMain {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("123-456", "홍길동", 1000.0);
        BankAccount account2 = account1.deposit(500.0);
        BankAccount account3 = account2.withdraw(200.0);

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println(account3.getBalance());
    }
}
