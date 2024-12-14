package section06;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Bank() {
        addNewCustomer("KIM", 010_1111_2222);
        addNewCustomer("LEE", 010_1111_2222);

        createAccount(1111, findCustomerByName("KIM"), 10000);
        createAccount(2222, findCustomerByName("LEE"), 10000);
    }

    public Customer addNewCustomer(String name, int phoneNumber) {
        Customer newCustomer = new Customer(name, phoneNumber);
        customers.add(newCustomer);
        return newCustomer;
    }

    public Account createAccount(int accountNumber, Customer customer, int initialBalance) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException("등록되지 않은 고객입니다.");
        }

        Account newAccount = new Account(accountNumber, customer, initialBalance);
        accounts.add(newAccount);
        return newAccount;
    }

    public Account findAccountByNumber(int accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        accountNumber + "번의 계좌가 존재하지 않습니다."
                ));
    }

    public Customer findCustomerByName(String name) {
        return customers.stream()
                .filter(holder -> holder.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        name + " 고객을 찾을 수 없습니다."
                ));
    }
}
