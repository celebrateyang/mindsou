package com.thread;

import org.junit.Test;

import java.util.Random;

/**
 * Created by 竹庆 on 2018/4/20.
 */
public class ThreadScopeShare {
    private static int data = 0;
    private static ThreadLocal<Integer> x = new ThreadLocal<>();
    public static void main(String[] args){
        for(int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadScopeShare.class) {
                        data = new Random().nextInt();
                        x.set(data);
                    System.out.println(Thread.currentThread().getName() + " has put data:" + data);
                    }
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }
    static class A{
        public int get(){
            int data = x.get();
            System.out.println("A from "+Thread.currentThread().getName()+" get data:"+data);
            return data;
        }
    }

    static class B{
        public int get(){
            int data = x.get();
            System.out.println("B from "+Thread.currentThread().getName()+"get data:"+data);
            return data;
        }
    }

    @Test
    public void haha(){
        for(int i =0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadScopeShare.class){
                    data = new Random().nextInt();
                    System.out.println("data==>"+data);}
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
