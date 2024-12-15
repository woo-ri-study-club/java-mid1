package section6;

public class Member {
    private final Long id;
    private final String name;
    private final Account account;

    private Member(Long id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    private Member (Long id, String name) {
        this(id, name, null);
    }

    public static Member createMember(Long id, String name) {
        return new Member(id, name);
    }

    public Member createAccountMember() {
        return new Member(this.id, this.name, Account.createAccount());
    }

    public long getAccountNumber() {
        return account.getAccountNumber();
    }

    public long getBalance() {
        return account.getBalance();
    }

    public void deposit(int amount) {
        account.deposit(amount);
    }

    public void withdraw(int amount) {
        account.withdraw(amount);
    }

    public boolean equalsAccountNumber(long accountNumber) {
        return account.getAccountNumber() == accountNumber;
    }
}
