package org.exps.measure;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

public class CPUMeasurement implements IMeasurement<Double>{
    ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
    private Map<String, double[]> measure = new HashMap<>();

    @Override
    public void start(String label) {
        long tInfo = 0;
        for(long id: threadMxBean.getAllThreadIds()) {            
            if(threadMxBean.getThreadInfo(id).getThreadName().equals(label)) {
                tInfo = id;
                break;
            }
        }
        measure.put(label, new double[]{threadMxBean.getThreadCpuTime(tInfo), 0});
    }

    @Override
    public void stop(String label) {
        long tInfo = 0;
        for(long id: threadMxBean.getAllThreadIds()) {
            if(threadMxBean.getThreadInfo(id).getThreadName().equals(label)) {
                tInfo = id;
                break;
            }
        }
        measure.get(label)[1] = threadMxBean.getThreadCpuTime(tInfo);

    }

    @Override
    public Double getResult(String label) {
        return measure.get(label)[1];
    }
}
