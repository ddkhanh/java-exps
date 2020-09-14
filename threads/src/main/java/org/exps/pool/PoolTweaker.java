package org.exps.pool;

public class PoolTweaker {
    public static void main(String[] args) {
        ThreadPoolRunner runner = new ThreadPoolRunner("ThreadPoolRunner", 10, 15, 300);
        runner.fillTask(200);
        runner.dumpInfo();
    }
}
