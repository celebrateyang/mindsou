package com.regex;

import org.junit.Test;

/**
 * Created by 竹庆 on 2019/6/30.
 */
public class ArrayRegx {
    @Test
    public void testArray() {
        String array = "[23,12,45,1,2,0]";
        System.out.println("array.matches=====>"+array.matches("^\\[((\\d)*,)*(\\d)+\\]$"));

    }
}
