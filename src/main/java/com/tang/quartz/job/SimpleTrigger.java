package com.tang.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTrigger implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("这是一个SimpleTrigger：" + simpleDateFormat.format(date));

        //获取jobKey、startTime、endTime
        Trigger trigger = jobExecutionContext.getTrigger();
        System.out.println("jobKey的名称：" + trigger.getJobKey().getName());
        System.out.println("jobKey组的名称：" + trigger.getJobKey().getGroup());
        System.out.println("任务开启时间："+simpleDateFormat.format(trigger.getStartTime()));
        System.out.println("任结束时间"+simpleDateFormat.format(trigger.getEndTime()));
    }
}
