package com.java8tests;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by liujq on 17-12-7.
 */
public class TestCollectorDisorder {

  public static void main(String[] args) {
    List<Integer> list = Lists.newArrayList(new Integer[]{3,9,4,5,6,7,8,1,2,10});
    Map<Integer,String> map = new HashMap<>();
    for(int i=12; i>3; i--){
      map.put(i,""+i);
    }
    map.put(3,"3");
    System.out.println(map);
    List collect = list.stream().map(e -> new String(map.get(e)))
      .collect(Collectors.toList());


    System.out.println(collect);
  }
}
