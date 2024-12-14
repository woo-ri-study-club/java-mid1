package section06;

import java.util.List;
import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer park = bank.addNewCustomer("PARK", 010_3333_4444);
        Account newAccount = bank.createAccount(3333, park, 10000);

        ATM atm = new ATM(bank);
        atm.run();
    }
}
