package org.exps.stackoverflow;

import org.exps.utilities.Dumpy;

import java.io.IOException;

public class StackConsumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length > 0) {
            String [] vars = new String[]{"java",
                    "-Xss"+args[0]+"m",
                    "-classpath",
                    Dumpy.getWorkingDir(),
            StackConsumer.class.getCanonicalName()};
            Dumpy.spawnProcess(vars);
        } else {
            StackConsumer consumer = new StackConsumer();
            consumer.consume(1);
        }
    }

    private void consume(int i) {
        System.out.printf("OMG! I'm going to hit myself %s times.\n", i);
        this.consume(i+1);
    }
}
