package com.basictype.example;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by liujq on 18-1-25.
 */
public class StringToBytes {
  private static String test = "hello world";

  public static void main(String[] args) throws Exception{
    stringToBytes();
  }

  public static void stringToBytes()throws IOException{
    byte[] bytes = test.getBytes("UTF-8");
    String s = new String(bytes,"UTF-8");
    System.out.println(test.equals(s));
    System.out.println(Arrays.equals(bytes,s.getBytes("UTF-8")));

    System.out.println(new String(s.getBytes("UTF-8")));
  }
}
