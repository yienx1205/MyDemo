package com.yienx.aspect;

import org.springframework.stereotype.Component;

/**
 * @Author wangyanbo29
 * @Date 2023/8/14
 * @Description
 */
@Component
public class Login {
    public String loginOnline(String name,String pwd){
        System.out.println(name +" 登录成功 "+", 登录的密码是 "+ pwd);
        return "loginOnline 成功";
    }
}
