package section6;

public interface Transaction {
    long execute(Atm atm, int amount, long targetAccountNumber);
}
