package com.tang.quartz.main;

import com.tang.quartz.job.SimpleDemo;
import com.tang.quartz.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

public class TriggerListenerDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SimpleDemo.class)
                .withIdentity("job1", "group1")
                .build();

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(2).
                        withRepeatCount(10))
                .build();

        //创建并且注册一个全局的TriggerListener
        //scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(), EverythingMatcher.allTriggers());

        //创建并且注册一个局部的TriggerListener
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(), KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1", "group1")));

        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }
}
