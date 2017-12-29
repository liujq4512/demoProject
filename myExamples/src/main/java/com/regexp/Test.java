package com.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liujq on 17-11-22.
 */
public class Test {

  public static Pattern pattern = Pattern.compile("\\d{4}:\\d{2}:\\d{2}\\s\\d{2}:\\d{2}:\\d{2}");
  public static void main(String[] args) {
    String s = "width=1400,height=1400,addtime=2017:11:18 12:25:53,lat=null,lng=null,make=Apple";
    Matcher matcher = pattern.matcher(s);
    while (matcher.find()){
      System.out.println(matcher.group(0));
    }



  }
}
