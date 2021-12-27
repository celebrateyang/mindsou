package com.bam;

import org.junit.Test;

public class testSound {
    public static void main(String[] args) {
        if(args!=null&&args.length>0) {
            System.out.println("args = " + args[0]);
        }
        System.out.print("\\a");

    }
    @Test
    public void testAbc(){
        System.out.println("fe");
    }
}
