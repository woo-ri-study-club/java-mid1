package section6;

public class Main {
    public static void main(String[] args) {
        Member initMember = Member.createMember(1L, "홍길동");
        Member member = initMember.createAccountMember();

        Member initTargetMember = Member.createMember(2L, "홍길동2");
        Member targetMember = initTargetMember.createAccountMember();

        Members livedMembers = new Members();
        livedMembers.addMember(member);
        livedMembers.addMember(targetMember);

        Atm atm = new Atm(member, livedMembers);

        // 타겟 계좌
        long targetAccountNumber = targetMember.getAccountNumber();

        Transaction depositTransaction = TransactionType.handler(TransactionType.DEPOSIT);
        int depositedBalance =  atm.executeTransaction(depositTransaction, 1000);
        System.out.println("depositedBalance = " + depositedBalance); // 잔금 1000원

        Transaction withdrawTransaction = TransactionType.handler(TransactionType.WITHDRAW);
        int withdrawnBalance =  atm.executeTransaction(withdrawTransaction, 500);
        System.out.println("withdrawnBalance = " + withdrawnBalance); // 잔금 500원

        Transaction transferTransaction = TransactionType.handler(TransactionType.TRANSFER);
        int transferredBalance =  atm.executeTransaction(transferTransaction, 200, targetAccountNumber);
        System.out.println("transferredBalance = " + transferredBalance); // 잔금 300원
    }
}
