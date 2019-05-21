package com.thread;

import org.junit.Test;

/**
 * Created by 竹庆 on 2018/4/15.
 */
public class TraditionalSyn {
    public String test="mao";

    @Test
    public void testLock() throws InterruptedException {
        final Outputer outputer = new Outputer();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("--------------");
                        while (true){
                            outputer.output("xiaozhu");
                            try {
                                Thread.sleep(10);
                                //System.out.println("XXXXX");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            outputer.output("123456789");
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            outputer.output("xiaozhu");
                        }
                    }
                }
        ).start();

        Thread.sleep(100000);
    }


     class Outputer {
        public void output(String name){

            int length = name.length();
            synchronized (this){
                for (int i = 0; i < length; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args){

        TraditionalSyn lockAnd  = new TraditionalSyn();
        lockAnd.init();

    }

    public void init(){
        final Outputer outputer = new Outputer();

        /*new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("--------------");
                        while (true){
                            outputer.output("xiaozhu");
                            try {
                                Thread.sleep(10);
                                //System.out.println("XXXXX");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            outputer.output("123456789");
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            outputer.output("xiaozhu");
                        }
                    }
                }
        ).start();*/
    }
}
