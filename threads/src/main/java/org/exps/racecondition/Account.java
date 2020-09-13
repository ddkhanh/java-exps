package org.exps.racecondition;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized void saving(int amount) {
        balance = balance + amount;
    }

    public synchronized void withDraw(int amount) {
        if(balance - amount > -1) {
            balance = balance - amount;
        }
    }
}
