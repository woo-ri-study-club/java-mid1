package section06;

import java.util.List;
import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer park = bank.addNewCustomer("3","PARK", "010-3333-4444");
        Account newAccount = bank.createAccount("3333", park, 10000L);

        ATM atm = new ATM(bank);
        atm.run();
    }
}
