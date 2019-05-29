package com.tang.quartz.main;

import com.tang.quartz.job.HelloWorld;
import com.tang.quartz.job.SimpleTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SimpleScheduler {
    public static void main(String[] args) throws SchedulerException {
        //设置任务开始时间
        Date startDate = new Date();
        //任务推迟3秒启动
        startDate.setTime(startDate.getTime() + 3000);

        //设置任务结束时间
        Date endDate = new Date();
        //任务开启后10秒结束
        endDate.setTime(endDate.getTime() + 13 * 1000);

        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(SimpleTrigger.class)
                .withIdentity("simpleJob", "jobGroup")
                .build();

        //创建触发器
        org.quartz.SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("simpleTrigger", "triggerGroup")
                //.startNow()
                .startAt(startDate)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(1))
                .build();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
    }
}
