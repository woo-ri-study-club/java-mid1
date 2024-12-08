public class MainBankAccount {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("123-456", "홍길동", 1000.0);
        BankAccount account2 = account1.withDeposit(500.0);
        BankAccount account3 = account2.withWithdraw(200.0);
//        BankAccount account4 = account2.withdraw(10000);
//        BankAccount account4 = account2.deposit(-10);
//        BankAccount account4 = account2.deposit(0);

        System.out.println(account1.getBalance()); // 1000.0
        System.out.println(account2.getBalance()); // 1500.0
        System.out.println(account3.getBalance()); // 1300.0
    }
}
