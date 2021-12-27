package com.bam;

public class Visible {
    //protected
    protected void me1(){
        System.out.println("me1 = " + true);
    }
    //default
    void me2(){
        System.out.println("me2 = " + true);
    }
    //private
    private void me3(){
        System.out.println("me3 = " + true);
    }
    public void me4(){
        System.out.println("public 到处可见 = " + true);
    }
}
