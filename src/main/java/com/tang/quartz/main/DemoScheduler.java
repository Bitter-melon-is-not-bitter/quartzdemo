package com.tang.quartz.main;

import com.tang.quartz.job.DemoJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class DemoScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建任务实例
        JobDetail jobDetail = JobBuilder.newJob(DemoJob.class)
                .withIdentity("jobDetail", "group")
                .usingJobData("message", "打印日志")//传递参数 名字message
                .usingJobData("count", 0)//这是无状态的count信息
                .build();

        System.out.println("名称：" + jobDetail.getKey().getName());
        System.out.println("组的名称：" + jobDetail.getKey().getGroup());
        System.out.println("任务类的地址：" + jobDetail.getJobClass().getName());
        //创建触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))
                .startNow()
                .usingJobData("message", "simple触发器")
                .withIdentity("trigger", "group")
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }
}
