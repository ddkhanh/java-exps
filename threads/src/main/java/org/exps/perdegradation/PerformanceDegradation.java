package org.exps.perdegradation;

public class PerformanceDegradation {
    public static void main(String[] args) {
        new PerformanceDegradation().parallelCal(20);
    }

    volatile int completed = 0;
    private void parallelCal(int counts) {
        final boolean[] flags = new boolean[counts];
        long start = System.nanoTime();
        long end = 0;

            for(int i = 0; i<counts; i++) {
                final int idx = i;
                new Thread(()-> {
                    flags[idx] = true;
                    completed++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "Thread-"+idx).start();
            }
            while (completed != counts) {
            }
            end = System.nanoTime();
            System.out.printf("Multithreading strategy completed within %s nano time\n", end-start);
            completed=0;
            start = System.nanoTime();
            new Thread(()->{
                for(int i=0; i<counts; i++) {
                    flags[i] = true;
                }
                completed++;
            }, "SingleThread").start();
            while (completed == 0) {
            }
            end = System.nanoTime();
        System.out.printf("Singlethread strategy completed within %s nano time\n", end-start);
    }
}
