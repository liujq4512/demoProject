package com.java8tests;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liujq on 18-1-10.
 */
public class TestCollectorsReducing {

  public static void main(String[] args) {
    Arrays.asList(1,2,3,4,5,6);
    List<Person> persons = Arrays.asList(new Person[]{new Person(1, 1), new Person(11, 1), new Person(22, 2), new Person(50, 2), new Person(30, 3)});
    BigDecimal collect = persons.stream()
      .collect(Collectors.reducing(BigDecimal.ZERO, Person::getMoney, BigDecimal::add));

    Map<Integer,BigDecimal> groupCount = persons.stream().collect(Collectors.groupingBy(Person::getGroup,Collectors.reducing(BigDecimal.ZERO,Person::getMoney,BigDecimal::add)));

    System.out.println(collect);
    System.out.println(groupCount);
  }
}



class Person{
  private BigDecimal money;
  private int group;

  public Person(int money,int group){
    this.money = BigDecimal.valueOf(money);
    this.group = group;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public int getGroup() {
    return group;
  }
}