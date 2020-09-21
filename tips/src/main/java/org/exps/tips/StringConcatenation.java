package org.exps.tips;

import org.exps.measure.IMeasurement;
import org.exps.measure.TimeMeasurement;

import java.util.concurrent.TimeUnit;

public class StringConcatenation implements ITip {
    private static int TER = 100000;
    private static IMeasurement<Long> timeCounter = new TimeMeasurement(TimeUnit.NANOSECONDS);

    private void should() {
        StringBuilder builder = new StringBuilder("");
        for(int i=0; i<TER; i++) {
            builder.append(i);
        }
    }


    private void shouldNot() {
        String s = new String("");
        for(int i=0; i<TER; i++) {
            s = s + i;
        }
    }

    @Override
    public void practice() {
        timeCounter.start("GOOD");
        should();
        timeCounter.stop("GOOD");
        timeCounter.start("BAD");
        shouldNot();
        timeCounter.stop("BAD");

    }

    @Override
    public void printResult() {
        long good = timeCounter.getResult("GOOD");
        long bad = timeCounter.getResult("BAD");

        System.out.printf("%s completed testing, result GOOD=%s (nano time) vs BAD=%s (nano time)\n", this.getClass().getName(),good, bad);
        System.out.printf("%s GOOD better than BAD %s times\n", this,getClass().getName(), (float)(bad/good));
    }

    public static void main(String[] args) {
        StringConcatenation main = new StringConcatenation();
        main.practice();
        main.printResult();
    }
}

