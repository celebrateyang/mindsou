package com.thread;

import org.junit.Test;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 竹庆 on 2018/4/15.
 */
public class CallableAndFuture {
    @Test
    public void testExc() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100000);
                return "hello";
            }
        });

        try {
            System.out.println(future.get());//这个future会一直等线程结束，得到结果。有什么用呢？
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMulti() {
        ExecutorService threadPools = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(threadPools);
        for(int i=0;i<10;i++) {
            final int j = i;
            completionService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return j;
                }
            });
        }

        for (int t=0;t<10;t++){
            try {
                System.out.println("completionService.take().get()=====>"+completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
