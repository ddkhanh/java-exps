package org.exps.oom;

import java.io.IOException;

public class MemPerThread {
    public static void main(String[] args) {
        System.out.println("This is a dummy class, going to trigger java process with the option \n -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics -version");
        try {
            String[] dumpArg = new String[]{"java", "-XX:+UnlockDiagnosticVMOptions", "-XX:NativeMemoryTracking=summary", "-XX:+PrintNMTStatistics", "-version"};
            new ProcessBuilder().command(dumpArg).inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
