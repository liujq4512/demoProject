package com.basictype.example;




import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liujq on 17-9-14.
 */
public class TestMapReference {
    public static void main(String[] args) {
        Map<String,List<String>> map = new HashMap<>();
        List list = new ArrayList();
        list.add("hello world");
        map.put("1",list);

        List<String> strings = map.get("1");
        strings.add("11111111");
        System.out.println(map);
    }
}
