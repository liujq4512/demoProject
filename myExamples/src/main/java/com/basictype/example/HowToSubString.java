package com.basictype.example;

/**
 * Created by liujq on 17-9-6.
 */
public class HowToSubString {



    public static void main(String[] args) {
        String s = "1003.100301.10030103.1003010302";
        System.out.println(s.substring(s.lastIndexOf(".")+1));

        System.out.println(s.substring(0,10));

    }
}
