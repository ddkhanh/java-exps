package org.exps.starvation;

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
        System.out.printf("%s is waiting to access the room\n", Thread.currentThread().getName());
        synchronized (LOCKED_ROOM) {
            System.out.printf("%s entered to the room!\n", Thread.currentThread().getName());
            while (true) {
                System.out.printf("%s still not finished yet...\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
