package com.basic.concurrent;

/**
 * Created by liujq on 17-11-16.
 */
public class ThreadsTest {

  public static void main(String[] args) {
    System.out.println(">>>>>>>>>....start..<<<<<<<<<<<<<<<");
    new Thread(()->{
      try {
        Thread.sleep(20000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("sub thread end");
    }).start();
    System.out.println("=============end=================");
  }
}
