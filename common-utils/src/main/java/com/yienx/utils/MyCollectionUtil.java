package com.yienx.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class MyCollectionUtil {

    /**
     * 找出两个集合中相同的元素
     * @param coll1 集合1
     * @param coll2 集合2
     * @return 集合1和集合2相同元素
     */
    public static <T> Set<T> getSame(Collection<T> coll1, Collection<T> coll2) {
        Set<T> csReturn = new HashSet<>();
        Collection<T> max = coll1;
        Collection<T> min = coll2;
        if (coll1.size() < coll2.size()) {
            max = coll2;
            min = coll1;
        }
        //直接指定大小,防止再散列
        Map<T, Integer> map = new HashMap<>(max.size());
        for (T t : max) {
            map.put(t, 1);
        }
        for (T t : min) {
            if (map.get(t) != null) {
                csReturn.add(t);
            }
        }
        return csReturn;
    }

    public static boolean isEmpty(Collection coll) {
        return CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(Map map) {
        return !CollectionUtils.isEmpty(map);
    }
}
