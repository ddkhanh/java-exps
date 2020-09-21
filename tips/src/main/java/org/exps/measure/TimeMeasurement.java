package org.exps.measure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeMeasurement implements IMeasurement<Long>{
    private Map<String, long[]> data = new HashMap<>();
    private TimeUnit unit;

    public TimeMeasurement(TimeUnit unit) {
        this.unit = unit;
    }

    @Override
    public void start(String label) {
        data.put(label, new long[] {unit.convert(System.currentTimeMillis(), unit), 0});
    }

    @Override
    public void stop(String label) {
        data.get(label)[1] = unit.convert(System.currentTimeMillis(), unit);
    }

    @Override
    public Long getResult(String label) {
        long[] range = data.get(label);
        return range[1] - range[0];
    }
}
