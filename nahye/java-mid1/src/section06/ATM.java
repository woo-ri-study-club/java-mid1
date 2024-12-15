package section06;

import java.util.InputMismatchException;
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
                String accountNumber = input.nextLine();
                Account myAccount = bank.findAccountByNumber(accountNumber);

                System.out.print("원하시는 거래를 입력해주세요 >> ");
                for (TransactionType type : TransactionType.values()) {
                    System.out.print(type.getDescription() + ", ");
                }
                System.out.println();
                String selectType = input.nextLine().trim();

                TransactionType type = TransactionType.findByDescription(selectType);
                processTransaction(myAccount, type);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processTransaction(Account myAccount, TransactionType type) {
        long amount;
        try {
            switch (type) {
                case DEPOSIT:
                    amount = processForInputAmount(myAccount, type);
                    myAccount.deposit(amount);
                    System.out.println(myAccount);
                    break;
                case WITHDRAW:
                    amount = processForInputAmount(myAccount, type);
                    myAccount.withdraw(amount);
                    System.out.println(myAccount);
                    break;
                case TRANSFER:
                    Account accountToTransfer = getAccountToTransfer(myAccount);
                    amount = processForInputAmount(myAccount, type);
                    myAccount.transfer(amount, accountToTransfer);
                    System.out.println(myAccount);
                    break;
                case BALANCE:
                    System.out.println(myAccount);
                    break;
                case EXIT:
                    System.out.println("ATM 거래를 종료합니다.");
                    System.exit(0);
                default:
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Account getAccountToTransfer(Account myAccount) {
        System.out.print("송금할 통장의 번호를 입력해주세요: ");
        String accountNumberToTransfer = input.nextLine();
        if (accountNumberToTransfer.equals(myAccount.getAccountNumber())) {
            throw new IllegalArgumentException("송금할 통장은 본인 통장과 달라야합니다.");
        }
        return bank.findAccountByNumber(accountNumberToTransfer);
    }

    private long processForInputAmount(Account myAccount, TransactionType type) {
        while (true) {
            try {
                System.out.print(type.getDescription() + "할 금액을 입력해주세요: ");
                long amount = input.nextLong();
                input.nextLine();
                if (amount <= 0) {
                    throw new IllegalArgumentException("유효한 값이 아닙니다.");
                }
                return amount;

            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        }
    }
}
