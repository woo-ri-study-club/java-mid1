package section6;

import static section6.TransactionType.*;

public class Atm {
    private final Member member;
    private final Members members;

    public Atm(Member member, Members members) {
        this.member = member;
        this.members = members;
    }

    public int deposit(long targetAccountNumber, int amount) {
        Member targetMember = members.findMember(targetAccountNumber);
        targetMember.deposit(amount);
        return targetMember.getBalance();
    }

    public int withdraw(long targetAccountNumber, int amount) {
        Member targetMember = members.findMember(targetAccountNumber);
        targetMember.withdraw(amount);
        return targetMember.getBalance();
    }

    public int transfer(long targetAccountNumber, int amount) {
        Member targetMember = members.findMember(targetAccountNumber);
        member.withdraw(amount);
        targetMember.deposit(amount);
        return member.getBalance();
    }

    public int executeTransaction(Transaction transaction, int amount, long targetAccountNumber) {
        return transaction.execute(this, amount, targetAccountNumber);
    }


    public int executeTransaction(Transaction transaction, int amount) {
        return this.executeTransaction(transaction, amount, member.getAccountNumber());
    }
}
