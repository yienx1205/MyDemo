package com.yienx.pattern.state;

/**
 * @Author wangyanbo29
 * @Date 2023/11/22
 * @Description
 */
public class StateTest {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
