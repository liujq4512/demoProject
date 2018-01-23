package com;

import javafx.scene.transform.Scale;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujq on 17-11-22.
 */

public class TestExample {


  @Test
  public void test(){
    List list = Arrays.asList(new Integer[]{1,2,3,4});
    System.out.println(list.subList(0,4));

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


  @Test
  public void testBigDecimal(){

    /*new MockUp<CfgService>() {
      @Mock
      public List<Long> getNewTTZPlanIds(){
        return Collections.singletonList(planId);
      }
    };*/

    System.out.println(new BigDecimal(1.012));
    System.out.println(new BigDecimal("1.012"));
    System.out.println(BigDecimal.valueOf(1.012));
    System.out.println(BigDecimal.TEN.divide(new BigDecimal(3), 2,BigDecimal.ROUND_HALF_UP));
    System.out.println(BigDecimal.valueOf(2222).negate().abs());
    System.out.println(BigDecimal.valueOf(21).movePointLeft(1));
  }

  @Test
  public void testMove(){
    System.out.println(1<<3);
    System.out.println(1>>2);
    System.out.println(4&2);
  }

  @Test
  public void testBoolean(){
    System.out.println(false ^ true);
    System.out.println(true ^ true);
    System.out.println(false ^ false);
  }

  @Test
  public void testClassCast(){
    Map map = new HashMap();
    map.put("intId",123);
    map.put("longId",1234l);
    map.put("stringId","233");

    Integer intId = (Integer)map.get("intId");
    Long longId = (Long)map.get("longId");
  }
}
