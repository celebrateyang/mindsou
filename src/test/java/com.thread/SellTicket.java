package com.thread;

import java.util.Random;

/**
 * Created by 竹庆 on 2018/4/24.
 */
public class SellTicket {
    public static void main(String[] args){
        TicketData ticketData = new TicketData();
        for (int i=0;i<30;i++){
        new Thread(ticketData).start();
        }
    }
}
class TicketData implements Runnable{
    int count =100;
    @Override
    public void run() {
        sellTicket();
    }
    public  void  sellTicket(){//加上synchronized 速度会很慢，不加上的话数据不同步=》锁竞争的问题，线程不能太多
        try {
            Thread.sleep(Math.abs(new Random().nextInt(100)*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        System.out.println("count=>"+count);
    }


}