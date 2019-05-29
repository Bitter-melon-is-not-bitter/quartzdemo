package com.tang.quartz.main;

import com.tang.quartz.job.SimpleDemo;
import com.tang.quartz.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

public class SimpleTriggerScheduler {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //Date startTime = new Date();

        Date endTime = new Date();
        endTime.setTime(endTime.getTime() + 63 * 1000);

        JobDetail jobDetail = JobBuilder.newJob(SimpleDemo.class)
                .withIdentity("job", "group")
                .build();

        //不做任何处理 只触发一次
        //Trigger trigger = TriggerBuilder.newTrigger()
        //        .startAt(startTime)
        //        .build();

        //在规定时间后每过一秒触发
        //SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
        //        .startAt(startTime)
        //        .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(1))
        //        .build();

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5)
                        .withRepeatCount(3))//每过5秒执行 只执行4次 默认要先执行一次后再处理所以说是执行四次
                //.startAt(startTime)
                .startNow()
                .endAt(endTime)
                .build();

        //scheduler.scheduleJob(jobDetail, trigger);
        //scheduler.start();

        //scheduler.scheduleJob(jobDetail, simpleTrigger);
        //scheduler.start();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        //创建并注册一个全局的JobListener
        //scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

        //创建并注册一个局部的JobListener
        scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("job", "group")));
        scheduler.start();


    }
}
