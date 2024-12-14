package section06;

import java.util.Scanner;

public class ATM {
    private final Bank bank;
    private final Scanner input = new Scanner(System.in);

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        while (true) {
            try {
                System.out.print("본인 통장의 번호를 입력해주세요: ");
                int accountNumber = input.nextInt();
                Account myAccount = bank.findAccountByNumber(accountNumber);
                input.nextLine();

                System.out.print("원하시는 거래를 입력해주세요 >> ");
                for (TransactionType type : TransactionType.values()) {
                    System.out.print(type.getDescription() + ", ");
                }
                System.out.println();
                String selectType = input.nextLine().trim();

                TransactionType type = TransactionType.findByDescription(selectType);

                if (type == TransactionType.EXIT) {
                    System.out.println("ATM 거래를 종료합니다.");
                    break;
                }
                processTransaction(myAccount, type);

            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processTransaction(Account myAccount, TransactionType type) {
        int amount;
        switch (type) {
            case DEPOSIT:
                System.out.print("입금할 금액을 입력해주세요: ");
                amount = input.nextInt();
                myAccount.deposit(amount);
                System.out.println(myAccount);
                break;

            case WITHDRAW:
                System.out.print("출금할 금액을 입력해주세요: ");
                amount = input.nextInt();
                myAccount.withdraw(amount);
                System.out.println(myAccount);
                break;

            case TRANSFER:
                System.out.print("송금할 통장의 번호를 입력해주세요: ");
                int accountNumberToTransfer = input.nextInt();
                if (accountNumberToTransfer == myAccount.getAccountNumber()) {
                    throw new IllegalArgumentException("송금할 통장은 본인 통장과 달라야합니다.");
                }
                Account accountToTransfer = bank.findAccountByNumber(accountNumberToTransfer);

                System.out.print("송금할 돈을 입력해주세요: ");
                amount = input.nextInt();
                myAccount.transfer(amount, accountToTransfer);
                System.out.println(myAccount);
                break;

            case BALANCE:
                System.out.println(myAccount);
                break;

            case EXIT:
                break;

            default:
        }
    }
}
