package com.commons.tools;

import org.apache.commons.jexl3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujq on 17-9-5.
 */
public class JexlTest {
    public static void main(String[] args){
        /*
 * 初始化一个Cat对象
 */
        Cat cat = new Cat() {
            {
                this.setAge(2);
                this.setName("Tom");
                this.setOwner(new People() {
                    {
                        this.setAge(24);
                        this.setName("yang");
                    }
                });
            }
        };

/*
 * 初始化一个List对象，列表里存入两个元素
 *     第一个元素是一个字符串
 *     第二个元素是一个整数
 */
        List list = new ArrayList();
        list.add("Hello world !");
        list.add(11);

        Map map = new HashMap();
        map.put("cat", cat);
        map.put("people",cat.getOwner());

/*
 * 初始化一个JexlContext对象，它代表一个执行JEXL表达式的上下文环境
 */
        JexlEngine jexlEngine =new JexlBuilder().create();
        JexlContext context = new MapContext();

/*
 * 向执行JEXL表达式的上下文环境的变量字典中存入两个变量
 *     键值 "tom" 对应变量 cat
 *     键值 "array" 对应变量 list
 */
        context.set("tom", cat);
        context.set("array", list);
        context.set("map", map);

        System.out.println(context.has("tom"));
/*
 * 定义要被求值的所有表达式
 */
        String[] expressions = new String[]{
                //嵌套属性
                "tom",
                //嵌套属性
                "tom.owner.name",
                //嵌套属性的方法调用，表达式求值结果为方法返回值
                "tom.owner.name.length()",
                "array[0].toUpperCase()",
                //内置通用方法size()，返回String，Map和List的长度
                "size(tom.owner.name)",
                //返回列表中第一个元素
                "array[0]",
                //+ 操作符 可用于字符串的连接
                "array[0].toUpperCase()+array[0]",
                //内置通用方法empty()，如果为空返回true，否则返回false
                "empty(array[0])",
                "empty(array[2])",
                //通过键值 'cat' 获取字典中相应的值
                "map['cat']",
                //嵌套属性
                "map['people'].name.length()"
        };

//对 expressions 中所有的表达式求值
        for(String expression : expressions){
            //用ExpressionFactory类的静态方法createExpression创建一个Expression对象
            JexlExpression e = jexlEngine.createExpression(expression);
            //对这个Expression对象求值，传入执行JEXL表达式的上下文环境对象
            Object obj = e.evaluate(context);
            //输出表达式求值结果
            System.out.println(expression+" = "+obj);
            System.out.println();
        }
    }
}

class Cat{
    private People owner;
    private String name;
    private int age;


    public People getOwner() {
        return owner;
    }

    public void setOwner(People owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class People{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
