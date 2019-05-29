package com.tang.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
    public String getName() {
        String simpleName = this.getClass().getSimpleName();
        System.out.println("监听器的名称为：" + simpleName);
        return simpleName;
    }

    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("job的名称为：" + name);
        System.out.println("Scheduler在jobDetail将要被执行时调用的方法");
    }

    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("Scheduler在jobDetail即将被执行，但又被TriggerListener否决时会调用该方法");
    }

    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("job的名称为：" + name);
        System.out.println("Scheduler在jobDetail被执行之后调用这个方法");
    }
}
