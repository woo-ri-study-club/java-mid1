package section06;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts = new HashMap<>();
    private Map<String, Customer> customers = new HashMap<>();

    public Bank() {
        addNewCustomer("1", "KIM", "010-1111-2222");
        addNewCustomer("2", "LEE", "010-3333-2222");

        createAccount("1111", findCustomerById("1"), 10000L);
        createAccount("2222", findCustomerById("2"), 10000L);
    }

    public Customer addNewCustomer(String id, String name, String phoneNumber) {
        if (customers.containsKey(id)) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        Customer newCustomer = new Customer(id, name, phoneNumber);
        customers.put(newCustomer.getId(), newCustomer);
        return newCustomer;
    }

    public Account createAccount(String accountNumber, Customer customer, Long initialBalance) {
        if (!customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("등록되지 않은 고객입니다.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("초기 잔액은 0원 이상이어야 합니다.");
        }
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("이미 존재하는 계좌번호입니다.");
        }

        Account newAccount = new Account(accountNumber, customer, initialBalance);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        return newAccount;
    }

    public Account findAccountByNumber(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException(accountNumber + "번의 계좌가 존재하지 않습니다.");
        }
        return account;
    }

    public Customer findCustomerById(String id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new IllegalArgumentException(id + "인 회원이 존재하지 않습니다.");
        }
        return customer;
    }
}