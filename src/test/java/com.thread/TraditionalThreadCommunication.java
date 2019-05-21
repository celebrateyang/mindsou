package com.thread;

/**
 * Created by 竹庆 on 2018/4/18.
 */
public class TraditionalThreadCommunication {
    public static void main(String[] args){
       final Business business = new Business();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int t=0;t<50;t++){
                            business.sub(t);
                        }
                    }
                }
        ).start();

        for(int t=0;t<50;t++){
            business.amain(t);
        }
    }
}
class Business{
    boolean shouldSub = true;

    public synchronized void sub(int t){
        while (!shouldSub){//用while代替if，防止伪唤醒时，程序被执行。即使被伪唤醒了，因为用了while,仍然去检查状态，是不是该我执行了，程序更健壮！
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
        System.out.println("子线程循环=>" + i + "  loop of " + t);
    }
        shouldSub = false;
        this.notify();
    }

    public synchronized void amain(int t){
        while(shouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程循环=>" + i + " loop of " + t);
        }
        shouldSub = true;
        this.notify();
    }
}