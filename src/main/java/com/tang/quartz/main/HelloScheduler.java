package com.tang.quartz.main;

import com.tang.quartz.job.HelloWorld;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
//import java.util.Date;

/**
 * 任务调度
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //1.调度器（Scheduler），从工厂中获取实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2.任务实例（JobDetail）
        JobDetail jobDetailo = JobBuilder.newJob(HelloWorld.class)
                .withIdentity("job1", "group1")//参数1：任务的名称（唯一实例） 参数2：任务组的名称
                .build();

        //3.触发器（Trigger）
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")//参数1：触发器名称唯一实例） 参数2：触发器组名和任务组名没有关系
                .startNow()//马上启动
                //.startAt() 设置多久启动
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(1))//设置每隔多少秒进行任务调度
                .build();

        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetailo, trigger);

        //启动
        scheduler.start();
        //scheduler.shutdown();关闭

        //Date date = new Date();
        //System.out.println(date);
        //
        //if (date == new Date("Mon May 27 17:34:30 CST 2019")){
        //    System.out.println("时间到了");
        //}
    }
}
