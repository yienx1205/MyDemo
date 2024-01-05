package com.yienx.test;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author wangyanbo29
 * @Date 2023/10/9
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String erps = "wangyanboliumei";
        System.out.println(Arrays.asList(erps.split(",")));
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
    }

    public void changeStr(String str) {

    }
}