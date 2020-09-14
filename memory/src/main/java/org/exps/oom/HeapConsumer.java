package org.exps.oom;

import org.exps.utilities.Dumpy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class HeapConsumer {
    private List<Object> dataSeeker = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length > 0) {
            try{
                new HeapConsumer().consumeHeap();
            } catch (Throwable e) {
                e.printStackTrace();
                Dumpy.dumpMemmoInfo();
            }
        } else {
            String file = HeapConsumer.class.getClassLoader().getResource(".").getFile();
            file = file.substring(1, file.length());
            System.out.printf("Spawn a new process to simulate OOM Error - loading this file %s\n", file);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("java", "-Xms1m", "-Xmx2m", "-XX:+UseParallelGC", "-XX:-HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=" + file + "oome.dump", "-classpath", file, HeapConsumer.class.getCanonicalName(), "gco");
            try {
                builder.inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void consumeHeap() {
        while (true) {
            dataSeeker.add(new String(String.valueOf(Math.random())));
        }
    }
}
