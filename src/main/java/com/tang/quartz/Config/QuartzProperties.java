package com.tang.quartz.Config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzProperties {
    public static void main(String[] args) throws SchedulerException {
        //创建工程实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        //创建配置工厂的属性对象
        Properties properties = new Properties();
        properties.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");//线程池定义
        properties.put("org.quartz.threadPool.threadCount", 5);//线程池数量 1-10
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        //加载属性
        stdSchedulerFactory.initialize(properties);
        scheduler.start();
    }
}
