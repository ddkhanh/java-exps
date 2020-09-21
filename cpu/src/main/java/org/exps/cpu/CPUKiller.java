package org.exps.cpu;

import java.util.Map;
import java.util.regex.Pattern;

public class CPUKiller {
    private static String LONG_PATTERN = "^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$";
    private static String SHORT_PATTERN = "[0-9]";

    public static void main(String[] args) {
        new CPUKiller().consumeCpu();
    }

    private void consumeCpu() {
        new Thread(() -> {
            while (true) {
                Pattern p = Pattern.compile(LONG_PATTERN);
                p.matcher("I'm hitting your CPU, be careful" + Math.random());
            }
        }, "LONG_PATTERN").start();

        new Thread(() -> {
            while (true) {
                Pattern p = Pattern.compile(SHORT_PATTERN);
                p.matcher("I'm hitting your CPU, be careful " + Math.random());
            }
        }, "SHORT_PATTERN").start();

        new Thread(() -> {
            while (true) {
                //Do nothing but having sleep
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "SLEEP_VERSION").start();
        for(int i=0; i<Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(() -> {
                while (true) {
                    Math.random();
                }
            }, "CPUKiller-"+(i+1)).start();
        }
    }
}
