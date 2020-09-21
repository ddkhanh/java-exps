package org.exps.measure;

public interface IMeasurement<T> {
    void start(String label);
    void stop(String label);
    T getResult(String label);
}
