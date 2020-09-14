package org.exps.utilities;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Date;

public class Dumpy {
    public static void dumpThread() {
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo ti : threadMxBean.dumpAllThreads(true, true)) {
            System.out.print(ti.toString());
        }
    }

    public static void dumpMemmoInfo() {
        // Get current size of heap in bytes
        int heapSize = (int) Runtime.getRuntime().totalMemory();

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        int heapMaxSize = (int) Runtime.getRuntime().maxMemory();

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        int heapFreeSize = (int) Runtime.getRuntime().freeMemory();

        System.out.printf("============================\nDumping heap info %s\n", new Date());
        System.out.printf("Get current size of heap in bytes: %s\n", heapSize);
        System.out.printf("Get maximum size of heap in bytes: %s\n", heapMaxSize);
        System.out.printf("Get amount of free memory within the heap in bytes: %s\n", heapFreeSize);
    }
}
