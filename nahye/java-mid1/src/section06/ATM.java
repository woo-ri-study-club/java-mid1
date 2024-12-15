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
        try {
            switch (type) {
                case DEPOSIT:
                    processDeposit(myAccount);
                    break;
                case WITHDRAW:
                    processWithdraw(myAccount);
                    break;
                case TRANSFER:
                    processTransfer(myAccount);
                    processBalance(myAccount);
                    break;
                case BALANCE:
                    processBalance(myAccount);
                    break;
                case EXIT:
                    processExit();
                    break;
                default:
                    throw new IllegalArgumentException("지원하지 않는 거래 유형입니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processDeposit(Account myAccount) {
        long amount = processForInputAmount(myAccount, TransactionType.DEPOSIT);
        myAccount.deposit(amount);
        processBalance(myAccount);
    }

    private void processWithdraw(Account myAccount) {
        long amount = processForInputAmount(myAccount, TransactionType.WITHDRAW);
        myAccount.withdraw(amount);
        processBalance(myAccount);
    }

    private void processTransfer(Account myAccount) {
        Account accountToTransfer = getAccountToTransfer(myAccount);
        long amount = processForInputAmount(myAccount, TransactionType.TRANSFER);;
        myAccount.transfer(amount, accountToTransfer);
    }

    private void processBalance(Account myAccount) {
        System.out.println(myAccount);
    }

    private void processExit() {
        System.out.println("ATM 거래를 종료합니다.");
        System.exit(0);
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
