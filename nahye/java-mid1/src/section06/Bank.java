package section06;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Bank() {
        addNewCustomer("1","KIM", "010-1111-2222");
        addNewCustomer("2","LEE", "010-3333-2222");

        createAccount("1111", findCustomerById("1"), 10000L);
        createAccount("2222", findCustomerById("2"), 10000L);
    }

    public Customer addNewCustomer(String id, String name, String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                throw new IllegalArgumentException("이미 존재하는 ID입니다.");
            }
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
            }
        }
        Customer newCustomer = new Customer(id, name, phoneNumber);
        customers.add(newCustomer);
        return newCustomer;
    }

    public Account createAccount(String accountNumber, Customer customer, Long initialBalance) {
        if (!customers.contains(customer)) {
            throw new IllegalArgumentException("등록되지 않은 고객입니다.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("초기 잔액은 0원 이상이어야 합니다.");
        }
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                throw new IllegalArgumentException("이미 존재하는 계좌번호 입니다.");
            }
        }

        Account newAccount = new Account(accountNumber, customer, initialBalance);
        accounts.add(newAccount);
        return newAccount;
    }

    public Account findAccountByNumber(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        accountNumber + "번의 계좌가 존재하지 않습니다."
                ));
    }

    public Customer findCustomerById(String id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        id + "의 고객을 찾을 수 없습니다."
                ));
    }
}
