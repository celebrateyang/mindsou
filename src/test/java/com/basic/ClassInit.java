package com.basic;

public class ClassInit {
    /*
    -------类初始化-----
    * main方法所在的类需要先加载和初始化
    * 一个子类要初始化需要先初始化父类
    * 一个类初始化就是执行clinit()方法，这个方法我们看不到，编译器会自动加上去
    * clinit方法由静态变量显式赋值代码和静态代码块组成，从上到下顺序执行
    *
    -------实例初始化-------
    首先执行init()方法，有几个构造器就会有几个init()方法
    子类的构造器都会调用父类的构造方法，不管你有没有写super()
    1)super()
    2)显式的实例变量赋值调用,比如下面的testa()方法
    3）非静态代码块 -》就是只用大括号括起来的代码，前面没有任何修饰，例如 { sout...}
    4）自己的构造方法
    ---------隐含知识点--------
    子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码(son给a赋值时，只会调用son的testa()方法)
    --------哪些方法不可以被子类重写？-------
    1) final 方法
    2）静态方法
    3）private 等子类中不可见方法
    * */
    int a = testa();
    ClassInit(){
        System.out.println("父亲无参构造方法");
    }
    ClassInit(int a,int b){
        System.out.println("父亲的两个参数构造方法");
    }
    {
        System.out.println("这个是父亲的非静态代码块，会在实例化的时候被调用");
    }
    public int testa(){
        System.out.println("父亲显式的实例变量赋值调用");
        return 1;
    }
}
