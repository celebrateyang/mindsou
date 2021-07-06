package com.basic;

public class VolatileVisible {
    int num = 0;
    public void addNum(){
         int x = num++;
        System.out.println("x = " + x);
        System.out.println("num = " + num);
    }
    public static void main(String[] args) {
        final VolatileVisible volatileVisible = new VolatileVisible();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("********************");
                volatileVisible.addNum();
            }
        }.start();
        
        while (volatileVisible.num==0){
            System.out.println(Thread.currentThread().getName()+",num=>"+volatileVisible.num);
        }
    }
}
