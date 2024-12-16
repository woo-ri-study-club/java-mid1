package section6;

public class Atm {
    private final Member member;
    private final Members members;

    public Atm(Member member, Members members) {
        this.member = member;
        this.members = members;
    }

    public long deposit(long targetAccountNumber, int amount) {
        Member targetMember = findTargetMember(targetAccountNumber);
        targetMember.deposit(amount);
        return targetMember.getBalance();
    }

    public long withdraw(long targetAccountNumber, int amount) {
        Member targetMember = findTargetMember(targetAccountNumber);
        targetMember.withdraw(amount);
        return targetMember.getBalance();
    }

    public long transfer(long targetAccountNumber, int amount) {
        Member targetMember = findTargetMember(targetAccountNumber);
        member.withdraw(amount);
        try {
            targetMember.deposit(amount);
        } catch (Exception e) {
            member.deposit(amount);
            throw e;
        }
        return member.getBalance();
    }

    public long executeTransaction(Transaction transaction, int amount, long targetAccountNumber) {
        return transaction.execute(this, amount, targetAccountNumber);
    }

    public long executeTransaction(Transaction transaction, int amount) {
        return this.executeTransaction(transaction, amount, member.getAccountNumber());
    }

    private Member findTargetMember(long targetAccountNumber) {
        Member targetMember = members.findMember(targetAccountNumber);
        return targetMember;
    }
}
