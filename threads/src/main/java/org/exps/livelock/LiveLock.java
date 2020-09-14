package org.exps.livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Same as Deadlock but in here, Bob and Alice want to eat both of fruits (orange and banana)
 * But their mother don't want this happen, she gave fruit to each but both
 */
public class LiveLock {
    private Lock ORANGE = new ReentrantLock(true);
    private Lock BANANA = new ReentrantLock(true);

    public static void main(String[] args) {
        LiveLock liveLock = new LiveLock();
        new Thread(liveLock::orange, "Bob").start();
        new Thread(liveLock::banana, "Alice").start();
    }

    private void orange() {
        try {
            while (true) {
                ORANGE.tryLock(3, TimeUnit.SECONDS);
                System.out.printf("%s can take ORANGE, trying to take BANANA\n", Thread.currentThread().getName());
                Thread.sleep(50);

                if (BANANA.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.printf("%s can take BANANA\n", Thread.currentThread().getName());
                } else {
                    System.out.printf("%s cannot take BANANA, put back ORANGE onto table\n", Thread.currentThread().getName());
                    ORANGE.unlock();
                    continue;
                }
                System.out.printf("%s take fruit first\n", Thread.currentThread().getName());
                break;
            }
            BANANA.unlock();
            ORANGE.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void banana() {
        try {
            while (true) {
                BANANA.tryLock(3, TimeUnit.SECONDS);
                System.out.printf("%s can take BANANA, trying to take ORANGE\n", Thread.currentThread().getName());
                Thread.sleep(50);

                if (ORANGE.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.printf("%s can take ORANGE\n", Thread.currentThread().getName());
                } else {
                    System.out.printf("%s cannot take ORANGE, put back BANANA onto table\n", Thread.currentThread().getName());
                    BANANA.unlock();
                    continue;
                }
                System.out.printf("%s take fruit second\n", Thread.currentThread().getName());
                break;
            }
            ORANGE.unlock();
            BANANA.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
