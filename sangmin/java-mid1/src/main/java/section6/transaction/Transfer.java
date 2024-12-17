package section6.transaction;

import section6.Atm;
import section6.Transaction;

public class Transfer implements Transaction {
    @Override
    public long execute(Atm atm, int amount, long targetAccountNumber) {
        return atm.transfer(targetAccountNumber, amount);
    }
}