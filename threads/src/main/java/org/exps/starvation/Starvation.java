package org.exps.starvation;

import java.util.Random;

/**
 * Same as Deadlock issue. This is a situation where multiple threads waiting to access a shared resource
 */
public class Starvation {
    private static Object LOCKED_ROOM = new Object();

    public static void main(String[] args) {
        Starvation starvation = new Starvation();
        new Thread(starvation::enterRoom, "John").start();
        new Thread(starvation::enterRoom, "Peter").start();
        new Thread(starvation::enterRoom, "Tom").start();
    }

    private void enterRoom() {
        int holdRoom = holdTime(Thread.currentThread().getName());
        int count=0;
        System.out.printf("%s is going to hold the room for %sms\n", Thread.currentThread().getName(), holdRoom);
        while (true) {
            System.out.printf("%s is waiting to access the room\n", Thread.currentThread().getName());
            synchronized (LOCKED_ROOM) {
                System.out.printf("%s have entered to the room!\n", Thread.currentThread().getName());
                try {
                    count++;
                    Thread.sleep(holdRoom);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s have left the room!\n", Thread.currentThread().getName());
            }
            System.out.printf("%s hold the room %s times\n", Thread.currentThread().getName(), count);
        }
    }
    private int holdTime(String name) {
        if("John".equals(name)) {
            return 50;
        }
        return 3000;
    }
}
