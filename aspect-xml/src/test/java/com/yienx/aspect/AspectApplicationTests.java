package com.yienx.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@SpringBootTest
class AspectApplicationTests {
    @Resource
    private Login login;

    @Test
    void contextLoads() {

    }

    @Test
    void testLogin() {
        login.loginOnline("yienx", "123456");
    }

}
