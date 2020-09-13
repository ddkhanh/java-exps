package org.exps.deadlock;

import org.exps.utilities.ThreadDump;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * A simple real-time example of deadlock is that suppose there are two babies Bob and Alice that are eating fruits (orange and banana).
 * During eating, Bod would like to eat orange, so he will use (lock) the orange.
 * Meanwhile, Alice want to eat banana, so she will use (lock) the banana.
 *
 * Currently, orange and banana are not occupied by any of the babies.
 * Now Bob want to eat banana too, But Alice will not give it because she is eating it currently.
 *
 * Later on, Alice want to eat orange as well but Bob will not give it as his eating is not completed because of waiting for the banana.
 * Thus, none of the friends will release stationary objects and both will wait infinitely for each other to release stationary.
 * This situation is called deadlock in Java.
 */
public class Deadlock {
    public static void main(String[] args) {
        Baby alice = new Baby("Alice", "BANANA", "ORANGE");
        Baby bob = new Baby("Bob", "ORANGE", "BANANA");
        alice.start();
        bob.start();

        try {
            Thread.sleep(3000);
            System.out.println("Going to dump threads....");
            ThreadDump.dumpThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
