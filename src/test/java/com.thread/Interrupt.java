package com.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 竹庆 on 2018/5/14.
 */
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("starting");
        ExecutorService exec = Executors.newCachedThreadPool();
         Future<?> future =  exec.submit(new Callable<Object>() {
             @Override
             public Object call() throws Exception {
                 Random random = new Random();
                 for(int i=0;i<1E8;i++){
                     if(Thread.currentThread().isInterrupted()){
                         System.out.println("we have been interruped");
                         break;
                     }
                     Math.sin(random.nextDouble());
                 }
                 return null;
             }
         });
        exec.shutdown();
        exec.shutdownNow();
        Thread.sleep(500);
        //future.cancel(true);
        exec.awaitTermination(1,TimeUnit.DAYS);
        System.out.println("finished");
    }
}
