package com.basic;

public class LazySingleton {
    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
    }
    private static volatile LazySingleton lazySingleton;//为什么用volatile 关键字？
    private LazySingleton(){
    }
    public static LazySingleton  getInstance(){
        if(lazySingleton==null){
            synchronized (LazySingleton.class) {//锁加在这里，而不是加在方法上，为什么？
                if(lazySingleton==null) {//为什么要再次判null?
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
