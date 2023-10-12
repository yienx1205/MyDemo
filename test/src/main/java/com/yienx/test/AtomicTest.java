package com.yienx.test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author wangyanbo29
 * @Date 2023/10/9
 * @Description
 */
public class AtomicTest {

    @Test
    public void test01() {
        AtomicBoolean flag = new AtomicBoolean(true);


    }
}


class TestWorker implements Runnable {
    private String name;
    public TestWorker(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("");
    }
}
