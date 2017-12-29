package com.DateAndTimeTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by liujq on 17-11-29.
 */
public class FomatTest {

  @Test
  public void test(){

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime startTime = LocalDateTime.parse("2017-11-03 16:42:15",formatter);
    System.out.println(startTime);


    System.out.println(BigDecimal.valueOf(2).compareTo(BigDecimal.valueOf(1))>0);
  }
}
