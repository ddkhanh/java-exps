package org.exps.utilities;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
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

    public static String getWorkingDir() {
        String file = Dumpy.class.getClassLoader().getResource(".").getFile();
        file = file.substring(1, file.length());
        return file;
    }

    public static void spawnProcess(String ...args) throws IOException, InterruptedException {
        System.out.printf("Going to spawn a new process %s\n", Arrays.deepToString(args));
        new ProcessBuilder().command(args).inheritIO().start().waitFor();
    }
}
