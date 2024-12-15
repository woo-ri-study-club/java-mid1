package section6.transaction;

import section6.Atm;
import section6.Transaction;

public class Withdraw implements Transaction {
    @Override
    public int execute(Atm atm, int amount, long targetAccountNumber) {
        return atm.withdraw(targetAccountNumber, amount);
    }
}
