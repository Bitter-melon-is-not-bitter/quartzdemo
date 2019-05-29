package com.tang.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleDemo implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("simple触发器，在规定时间执行规定的任务");
        System.out.println("有点小毛病");
    }
}
