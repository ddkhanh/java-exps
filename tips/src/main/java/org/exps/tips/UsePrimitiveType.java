package org.exps.tips;

import org.exps.measure.MemMeasurement;
import org.exps.utilities.Dumpy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UsePrimitiveType implements ITip{
    private static int TER = 100000;
    private Map<Integer, String> dataType = new HashMap();
    private Map<Integer, byte[]> primitiveType = new HashMap();
    private MemMeasurement primitiveMeasurement = new MemMeasurement(primitiveType);
    private MemMeasurement dataTypeMeasurement = new MemMeasurement(dataType);
    private static String DATA = new String("20charssssssssssssss");

    @Override
    public void practice() {
        primitiveMeasurement.start("primitive");
        for(int i=0; i<TER; i++) {
            primitiveType.put(i, DATA.getBytes(StandardCharsets.US_ASCII));
        }
        primitiveMeasurement.stop("primitive");
        dataTypeMeasurement.start("datatype");
        for(int i=0; i<TER; i++) {
            dataType.put(i, new String(DATA.getBytes()).toString());
        }
        dataTypeMeasurement.stop("datatype");
    }

    @Override
    public void printResult() {
        long primitiveSize = primitiveMeasurement.getResult("primitive");
        long dataTypeSize = dataTypeMeasurement.getResult("datatype");
        System.out.printf("%s completed, Primitive type size=%s bytes vs Data type size=%s bytes\n", this.getClass().getName(), primitiveSize, dataTypeSize);
        System.out.printf("%s completed, Primitive type has memory smaller than %s times\n", this.getClass().getName(), (double)((dataTypeSize/primitiveSize)));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        args = new String[]{"org"};
        if(args.length > 0) {
            UsePrimitiveType main = new UsePrimitiveType();
            main.practice();
            main.printResult();
            Thread.sleep(TimeUnit.SECONDS.toMillis(5000));
            return;
        }
        String[] vars = new String[]{"java", "-classpath", Dumpy.getWorkingDir(), UsePrimitiveType.class.getCanonicalName(), "dump"};
        Process process = Dumpy.spawnProcess(false, vars);
        long pid = process.pid();
        Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        vars = new String[]{"jmap", "-dump:all,format=b,file=data.jmap", String.valueOf(pid)};
        Dumpy.spawnProcess(vars);
        process.waitFor();
    }
}
