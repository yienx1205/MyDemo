package com.yienx.request;

import com.yienx.aop.AssertOK;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class Login {

    @AssertOK(isLogin = "ture")
    public String login(String username,String password){
        System.out.println(username+" 登录成功 "+", 登录的密码是 "+password);
        return "登录成功";
    }

    @AssertOK(isLogin = "false")
    public String loginOnline(String name,String pwd){
        // AOP不能自调
        String login = ((Login)AopContext.currentProxy()).login(name, pwd);
        // String login = login(name, pwd);
        System.out.println("login 的返回值："+login);
        return "loginOnline success";
    }
}
