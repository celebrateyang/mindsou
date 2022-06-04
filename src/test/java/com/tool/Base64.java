package com.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Base64 {
    public static void main(String[] args) throws IOException {
        //base64编码
        System.out.println("请输入需要编码的字符串:");
        String inputString = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String encodedStr = java.util.Base64.getEncoder().encodeToString(inputString.getBytes());
        System.out.println("encodedStr = " + encodedStr);
        //base64解码
        System.out.println("请输入要解码的字符串:" );
        String inputStringDecode = new BufferedReader(new InputStreamReader(System.in)).readLine();
        byte[] decodeBytes = java.util.Base64.getDecoder().decode(inputStringDecode);
        String s = new String(decodeBytes);
        System.out.println("decodeString = " + s);
    }
}
