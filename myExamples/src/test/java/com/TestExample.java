package com;

import org.junit.Test;

/**
 * Created by liujq on 17-11-22.
 */
public class TestExample {


  @Test
  public void test(){
    System.out.println("---------");
  }

  @Test
  public void testMathRandom(){
    int n=0;
    for(int i=0;i<10000;i++){
      double random = Math.random();
      if(random>0.9){
        n++;
      }
    }
    System.out.println(n);
  }
}
