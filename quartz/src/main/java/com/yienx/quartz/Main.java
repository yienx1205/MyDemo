package com.yienx.quartz;

/**
 * @Author wangyanbo29
 * @Date 2023/8/4
 * @Description
 */
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;


/**
 * @Author wangyanbo29
 * @Date 2023/7/8
 * @Description
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 1、创建调度器 Scheduler
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        // 2、创建 JobDetail 实例，并与 MyJob 类绑定
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        // 3、构建 Trigger 实例，每隔 30s 执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // 4、执行，开启调度器
        scheduler.scheduleJob(job, trigger);
        System.out.println(System.currentTimeMillis());
        scheduler.start();

        // 主线程睡眠1分钟，然后关闭调度器
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println(System.currentTimeMillis());

    }

}