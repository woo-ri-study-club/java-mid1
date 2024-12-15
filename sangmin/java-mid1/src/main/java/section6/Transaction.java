package section6;

public interface Transaction {
    int execute(Atm atm, int amount, long targetAccountNumber);
}
