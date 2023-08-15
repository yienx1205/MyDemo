package com.yienx.request;

import com.yienx.aop.AssertOK;
import org.springframework.stereotype.Component;

@Component
public class QueryAccount {

    @AssertOK
    public String queryAccount(){

        System.out.println("账户查询成功....");
        return "success";
    }
}
