package com.example.concurrency.ex08;

import java.util.concurrent.*;

public class ThreadDeadLock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(() ->  "header.html");
            footer = exec.submit(() -> "footer.html");


            System.out.println("header = " + header.get());
//            deadlock 발생 지점
            return "";// header.get() + "," + footer.get() ;
        }

    }

    public static void main(String[] args) throws Exception {
        ThreadDeadLock.RenderPageTask task = new ThreadDeadLock().new RenderPageTask();
        FutureTask futureTask = new FutureTask(task);
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }
}
