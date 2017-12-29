package com.regexp;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liujq on 17-11-22.
 */
public class RegexpTest {


  public static void main(String[] args) {
    String s = "/^\\d+(?:\\.\\d{1,2})?$/";
    Pattern p = Pattern.compile(s);
    Matcher matcher = p.matcher("1.2");
    System.out.println(matcher.find());
  }
}
