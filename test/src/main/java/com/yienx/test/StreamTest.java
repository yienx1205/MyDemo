package com.yienx.test;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author wangyanbo29
 * @Date 2024/1/5
 * @Description
 */
public class StreamTest {
    @Test
    public void listToMap() {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1");
        Apple apple12 = new Apple(1,"苹果2");
        Apple apple2 =  new Apple(2,"香蕉");
        Apple apple3 =  new Apple(3,"荔枝");

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        // List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println("groupBy: "+groupBy);

        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k1));
        System.out.println("appleMap: " + appleMap);
    }

}

@Data
class Apple {
    private Integer id;
    private String name;
    public Apple(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}