package com.yienx.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Author wangyanbo29
 * @Date 2023/7/8
 * @Description 新建任务，实现Job接口，execute中可以执行需要定时的任务
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("执行任务 " + new Date());
    }
}
