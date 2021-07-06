package com.basic;

public class ClassInitSon extends ClassInit{
    ClassInitSon(String aa){
        System.out.println("儿子有参构造方法");
    }

    ClassInitSon(int a,int b){
        super(a,b);//如果想调用父亲的有参构造方法，则这里需要显式调用。不写的化会默认首先调用父亲的无参构造方法
        System.out.println("儿子两个参数构造方法");
    }
    ClassInitSon(){
        System.out.println("儿子无参构造方法");
    }

    public int testa(){
        System.out.println("儿子重写了父亲的testa()方法");
        return 2;
    }
    public static void main(String[] args) {
        new ClassInitSon("wahaha");
        System.out.println("-------------");
        new ClassInitSon(1,2);
    }
}
