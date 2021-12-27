package com.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupTest {
    @Test
    public void testGroups(){
        String content = "bamboo123yang";
        String regex = "(?:\\d{3})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("找到了 => " + matcher.group(0));
            //System.out.println("分组 => " + matcher.group(1));
        }
    }
    @Test
    public void testEnglishMoney(){
        String content = "2344524567";
        String regex = "(?=(\\d{3})+(?!\\d))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("找到了 => " + matcher.group(0));
            System.out.println("分组 => " + matcher.group(1));
        }

        String s = content.replaceAll(regex, ",");
        System.out.println("s = " + s);
    }


}
