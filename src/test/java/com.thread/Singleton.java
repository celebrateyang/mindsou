package com.thread;

/**
 * Created by 竹庆 on 2018/4/24.
 */
//饿汉模式
public class Singleton {
    private Singleton(){}
    public static synchronized Singleton getInstance(){
        if(instance==null){
            instance = new Singleton();
        }
        return instance;
    }
    private static Singleton instance = null;//new Singleton();-》饱汉模式
}
