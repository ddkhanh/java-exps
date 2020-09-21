package org.exps.oom;

import org.exps.utilities.Dumpy;

import java.io.IOException;

public class MemPerThread {
    public static void main(String[] args) {
        try {
            System.out.println("This is a dummy class, going to trigger java process with the option \n -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics -version");
            String[] dumpArg = new String[]{"java", "-XX:+UnlockDiagnosticVMOptions", "-XX:NativeMemoryTracking=summary", "-XX:+PrintNMTStatistics", "-version"};
            Dumpy.spawnProcess(dumpArg);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
