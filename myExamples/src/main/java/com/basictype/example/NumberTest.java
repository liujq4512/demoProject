package com.basictype.example;

/**
 * Created by liujq on 18-1-25.
 */
public class NumberTest {

  public static void main(String[] args) {
    Object o1=1111111111111111l;
    Object o2=11;

    Number number = (Number)o1;
    Number number2 = (Number)o2;
    Long lo = number.longValue();
    Integer integer = number2.intValue();
    System.out.println(lo + "  " + number2.longValue());
  }
}
