package com.yienx.mockito;

import java.util.Random;

/**
 * @Author wangyanbo29
 * @Date 2024/2/20
 * @Description
 */
public class DemoDao {
    public int getDemoStatus(){
        return new Random().nextInt();
    }
}
