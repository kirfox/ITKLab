package ru.example.spring;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private final int id;
    static final AtomicInteger ID_GENERATOR  = new AtomicInteger(0);
    private int balance;

    public BankAccount(int balance) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.balance = balance;
    }

    public synchronized void deposit(int cash) {
         if (cash > 0) balance += cash;
         else throw new IllegalArgumentException();
    }

    public synchronized void withdraw (int cash) {
        if (cash < 0) throw new IllegalArgumentException();
        else if (cash > balance)  throw new IllegalStateException();
        else balance -=cash;
    }

    public int getId() {
        return id;
    }

    public synchronized int getBalance() { return balance;}
}
