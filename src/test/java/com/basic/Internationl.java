package com.basic;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by 竹庆 on 2019/5/19.
 */
public class Internationl {
    public static void main(String[] args){
        long a= 18;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        System.out.println(nf.format(a));
    }
}
