package com.basic;

/*
* 饿汉式不存在线程安全问题，懒汉式需要解决线程安全问题
* */
public class LazySingleton {
    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
        System.out.println(LazySingleton.getInstance()==LazySingleton.getInstance());
        for(int i=0;i<10;i++){
            new Thread(()->{
                LazySingleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
    /*
    1. 分配内存对象
    2. 初始化对象
    3. 设置instance指向刚分配的内存地址，此时instance==内存地址
    指令重排
    1. 分配内存对象
    3. 设置instance指向刚分配的内存地址，此时instance==内存地址，此时对象初始化还没有完成
    2. 初始化对象
     */
    private static volatile LazySingleton lazySingleton;//为什么用volatile 关键字？ 如果不加volatile,可能会使后面的线程拿到一个半初始化的对象
    private LazySingleton(){
        System.out.println(Thread.currentThread().getName()+"\t这里是构造方法LazySingleton(),应该只被打印一次 ");
    }
    public static LazySingleton  getInstance(){
        if(lazySingleton==null){
            synchronized (LazySingleton.class) {//锁加在这里，而不是加在方法上，为什么？->提高效率，只有null的时候才需要创建，从而需要锁
                if(lazySingleton==null) {//为什么要再次判null?
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }


}
