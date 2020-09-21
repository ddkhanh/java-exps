package org.exps.oom;

import org.exps.utilities.Dumpy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GCHeapConsumer {
    private List<Object> dataSeeker = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length > 0) {
            try{
                new GCHeapConsumer().consumeHeap();
            } catch (Throwable e){
                e.printStackTrace();
                Dumpy.dumpMemmoInfo();
            }
        } else {
            String file = GCHeapConsumer.class.getClassLoader().getResource(".").getFile();
            file = file.substring(1, file.length());
            System.out.printf("Spawn a new process to simulate OOM Error - loading this file %s\n", file);

            String[] vars = new String[]{"java", "-Xms1m", "-Xmx2m", "-XX:+UseParallelGC", "-XX:-HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=" + file + "oome.dump", "-classpath", file, GCHeapConsumer.class.getCanonicalName(), "gco"};
            try {
                Dumpy.spawnProcess(vars);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void consumeHeap() {
        Random random = new Random();
        List<String> interned = new ArrayList<>();
        while (true) {
            int length = random.nextInt(100);
            StringBuilder builder = new StringBuilder();
            String sample = "Hello a good day";

            for (int i = 0; i < length; i++) {
                builder.append(sample.charAt(random.nextInt(sample.length())));
            }
            interned.add(builder.toString());
        }
    }


}
