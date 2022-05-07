package com.example.concurrency.ex08;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Logger log = Logger.getLogger("TimeThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info("thread " + t + ", start " + r);
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try{
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info("thread " + t + ", end " + r + ", time=" + taskTime);
        }finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            log.info("Terminated: avg time = " + totalTime.get() / numTasks.get());
        } finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        TimingThreadPool threadPool = new TimingThreadPool(1,
                5,
                10,
                TimeUnit.SECONDS,
                blockingQueue);
        Runnable thread = new testThread();

        for (int i = 0; i < 5; i++) {
            threadPool.execute(thread);
        }
        threadPool.shutdown();

    }

    static class testThread implements Runnable{

        @Override
        public void run() {
            int num = 0;
            for (int i = 0; i < 10; i++) {
                System.out.println("This Thread = " + Thread.currentThread().getName() + ", num =" + num++);
            }
        }
    }
}
