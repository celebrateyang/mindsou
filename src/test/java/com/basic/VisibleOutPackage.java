package com.basic;

import com.bam.Visible;

public class VisibleOutPackage {
    public static void main(String[] args) {
        Visible visible = new Visible();
        //此时me1不可见，因为它是protected修饰，外包不可见，但是子类可见，即使子类处于外部包中，见VisibleChild1
        //me2也不可见，应为它是default修饰，外部包和子类都不可见
        //me3也不可见，因为它是private修饰，外部包，子类，本包都不可见
        visible.me4();
    }
}
