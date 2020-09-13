package org.exps.pool;

import java.util.concurrent.*;

public class ThreadPoolRunner {
    private static volatile int count=1;
    private ThreadPoolExecutor executor;
    public ThreadPoolRunner(final String name, int core, int pool, int queueSize) {
        executor = new ThreadPoolExecutor(core, pool, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
        executor.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, name + "-" + count++) ;
            }
        });
    }

    public void fillTask(int count) {
        for(int i=1; i<count; i++) {
           executor.execute(new TaskRunner((i)));
        }
    }

    public void dumpInfo() {
        while(executor.getActiveCount() > 0) {
            System.out.printf("No. core threads: %s\n", executor.getActiveCount());
            System.out.printf("No. created threads: %s\n", count-1);
            System.out.printf("No. task awaiting: %s\n", executor.getQueue().size());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    private class TaskRunner implements Runnable{
        private int id;

        public TaskRunner(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.printf("%s is running with task id=%s\n", Thread.currentThread().getName(), id);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s completed with task id=%s\n", Thread.currentThread().getName(), id);
        }
    }

    public static void main(String[] args) {
        ThreadPoolRunner runner = new ThreadPoolRunner("ThreadPoolRunner", 10, 15, 300);
        runner.fillTask(200);
        runner.dumpInfo();
    }
}
