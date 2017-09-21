package com.basictype.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liujq on 17-9-13.
 */
public class ListRemoveItem {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        useIterator(list);
        //useForEach(list);
    }

    //用这种方式会导致list 索引发生改变，导致有的没有遍历到
    public static void useForEach(List list){
        for(int i=0;i<list.size(); i++){
            System.out.println(">>>>>>>>" + list.get(i));
            list.remove(i);
        }
    }

    //用这种方式可实现
    public static void useIterator(List list){
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){

            System.out.println("------" + iterator.next());
            // hasNext() 可以多次调用，不影响
            if(iterator.hasNext()){
                System.out.println("......");
                iterator.remove();
            }
        }
    }
}
