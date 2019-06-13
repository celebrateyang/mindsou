package com.mindsou.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by 竹庆 on 2019/6/13.
 */
@Service
public class AsycService {

    @Async
    public void async(){

        System.out.println("Thread.currentThread().getName()=====>"+Thread.currentThread().getName());
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
