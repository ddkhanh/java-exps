package org.exps.racecondition;

import java.util.Random;

public class Client extends Thread{
    private final Account account;

    public Client(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        int amount = new Random().nextInt(10);
        int currentBal = account.getBalance();
        for (int i=1; i<10; i++) {
            System.out.printf("%s check current balance info %s VND\n", getName(), account.getBalance());

            if(Math.random() > 0.5) {
                account.withDraw(amount);
                System.out.printf("%s decided to withdraw with the amount %s.\n", getName(), amount);
            } else {
                account.saving(amount);
                 System.out.printf("%s decided to save the money to bank with the amount %s.\n", getName(), amount);
            }
            System.out.printf("%s check balance info current before=%s vs after=%s\n", getName(), currentBal, account.getBalance());
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
