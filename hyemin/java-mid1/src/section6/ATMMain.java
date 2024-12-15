package src.section6;

public class ATMMain {
    public static void main(String[] args) {
        BankAccount senderAccount = new BankAccount("123-456-789", 100000);
        BankAccount receiverAccount = new BankAccount("987-654-321", 50000);

        Transfer transfer = new Transfer(senderAccount, receiverAccount, 20000);

        transfer.execute();

        System.out.println("송금 상태: " + transfer.getStatus());
        System.out.println("송금 후 발신자 잔액: " + senderAccount.getBalance() + "원");
        System.out.println("송금 후 수신자 잔액: " + receiverAccount.getBalance() + "원");
    }
}
