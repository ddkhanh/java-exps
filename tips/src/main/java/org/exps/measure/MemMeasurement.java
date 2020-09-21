package org.exps.measure;

import net.sourceforge.sizeof.SizeOf;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;


public class MemMeasurement implements IMeasurement<Long>{
    private Map<String, long[]> measure = new HashMap<>();
    private Object data;

    public MemMeasurement(Object data) {
        SizeOf.skipStaticField(true); //java.sizeOf will not compute static fields
        SizeOf.skipFinalField(true); //java.sizeOf will not compute final fields
        SizeOf.skipFlyweightObject(true); //java.sizeOf will not compute well-known flyweight objects
        this.data = data;
    }

    @Override
    public void start(String label) {
        measure.put(label, new long[]{SizeOf.deepSizeOf(data), 0});
    }

    @Override
    public void stop(String label) {
        measure.get(label)[1] = SizeOf.deepSizeOf(data);

    }

    @Override
    public Long getResult(String label) {
        long[] ranges = measure.get(label);
        return ranges[1];
    }
}
