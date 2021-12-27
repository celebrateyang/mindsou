package com.bam;

public class VisibleChild2 extends Visible{
    public static void main(String[] args) {
        Visible visible = new Visible();
        visible.me1();
        visible.me2();
        //但是此时me3不可见，因为是private修饰，本包，子类，外部包都不可见

    }
}
