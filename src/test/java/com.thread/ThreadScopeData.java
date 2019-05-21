package com.thread;

import org.junit.Test;

import java.util.Random;

/**
 * Created by 竹庆 on 2018/4/20.
 */
public class ThreadScopeData {
    private static int data = 0;
    private static ThreadLocal<Integer> x = new ThreadLocal<>();
    public static void main(String[] args){
        for(int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadScopeData.class) {
                        data = new Random().nextInt();
                        x.set(data);
                    System.out.println(Thread.currentThread().getName() + " has put data:" + data);
                        ThreadInstacneData.getThreadInstanceData().setAge(data);
                        ThreadInstacneData.getThreadInstanceData().setName(data);
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
            ThreadInstacneData threadInstacneData = ThreadInstacneData.getThreadInstanceData();
            System.out.println("A from "+Thread.currentThread().getName()+"线程共享的数据对象是：name=>"+threadInstacneData.getName()+
            " age=>"+threadInstacneData.getAge());
            return data;
        }
    }

    static class B{
        public int get(){
            int data = x.get();
            System.out.println("B from "+Thread.currentThread().getName()+"get data:"+data);
            ThreadInstacneData threadInstacneData = ThreadInstacneData.getThreadInstanceData();
            System.out.println("B from "+Thread.currentThread().getName()+"线程共享的数据对象是：name=>"+threadInstacneData.getName()+
                    " age=>"+threadInstacneData.getAge());
            return data;
        }
    }


    @Test
    public void haha(){
        for(int i =0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (ThreadScopeData.class){
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

class ThreadInstacneData{
    private ThreadInstacneData(){}
    public static ThreadInstacneData getThreadInstanceData(){
        if(threadInstanceData.get()==null)
            threadInstanceData.set(new ThreadInstacneData());
        return threadInstanceData.get();

    }

   private static ThreadLocal<ThreadInstacneData> threadInstanceData = new ThreadLocal<>();


    int age;
    int name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
