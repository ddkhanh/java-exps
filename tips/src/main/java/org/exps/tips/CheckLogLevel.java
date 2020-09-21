package org.exps.tips;

import ch.qos.logback.classic.Level;
import org.exps.measure.CPUMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckLogLevel implements ITip{
    private static final Logger logger = LoggerFactory.getLogger(CheckLogLevel.class);
    private int TER = 300000;
    private CPUMeasurement measurement = new CPUMeasurement();

    {
        ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) logger;
        log.setLevel(Level.INFO);
    }
    @Override
    public void practice() {
        List<String> data = new ArrayList<>();
        for(int i=0; i<TER; i++) {
            data.add(String.valueOf(Math.random() * i));
        }
        System.out.printf("Done prepare data!\n");
        new Thread(()->{
            while (true) {
                String s = Arrays.deepToString(data.toArray());
                logger.debug(s);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BAD").start();

        new Thread(()->{
            while (true) {
                if(logger.isDebugEnabled()) {
                    logger.debug(Arrays.deepToString(data.toArray()));
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "GOOD").start();

    }

    @Override
    public void printResult() {
        measurement.start("GOOD");
        measurement.stop("GOOD");
        measurement.start("BAD");
        measurement.stop("BAD");
        System.out.printf("CPU usage GOOD %s ns vs BAD %s ns", measurement.getResult("GOOD"), measurement.getResult("BAD") );
    }

    public static void main(String[] args) throws InterruptedException {
        CheckLogLevel main = new CheckLogLevel();
        main.practice();
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        main.printResult();
    }
}
