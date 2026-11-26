package ru.example.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {

    private final Object lock = new Object();

    private final Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(int balance) {
        synchronized (lock) {
            BankAccount newAccount = new BankAccount(balance);
            accounts.put(newAccount.getId(), newAccount);
            return newAccount;
        }
    }

    public void transfer(BankAccount from, BankAccount to, int transferAmount) {
        synchronized (lock) {
            if (from == to) {
                throw new IllegalArgumentException();
            }

            from.withdraw(transferAmount);
            to.deposit(transferAmount);
        }
    }

    public int getTotalBalance() {
        synchronized (lock) {
            int totalSum = 0;

            for (BankAccount account : accounts.values()) {
                totalSum = Math.addExact(totalSum, account.getBalance());
            }

            return totalSum;
        }
    }
}