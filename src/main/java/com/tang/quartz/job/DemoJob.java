package com.tang.quartz.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution//多次调用job时都会对job进行一次持久化，保存数据的信息 也就是有状态（没加上这个注解，也就是无状态）
public class DemoJob implements Job {


    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    public DemoJob() {
        System.out.println("job调用每次创建一个新实例，调用完之后会被释放，被垃圾回收机制回收");
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        System.out.println(date.toString());

        //获取JobDetail内容
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("工作任务名称：" + key.getName() +
                " 工作任务组：" + key.getGroup() +
                " 任务类名称（带路径）：" + jobExecutionContext.getJobDetail().getJobClass().getName() +
                " 任务类名称（不带路径）：" + jobExecutionContext.getJobDetail().getJobClass().getSimpleName()
        );

        //从JobDetail中获取jobDataMap的数据
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobDetailMessage = jobDataMap.getString("message");
        System.out.println("JobDetail（任务）中的jobDataMap：" + jobDetailMessage);

        //从Trigger对象获取jobDataMap的数据
        JobDataMap triggerJobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
        String triggerMessage = triggerJobDataMap1.getString("message");
        System.out.println("triggerMessage（触发器）中的jobDataMap：" + triggerMessage);

        //获取Trigger的内容
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        System.out.println("触发器名称：" + triggerKey.getName());
        System.out.println("触发器的组：" + triggerKey.getGroup());

        //获取其他
        System.out.println("当前任务执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobExecutionContext.getFireTime()));
        System.out.println("下一次任务执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jobExecutionContext.getNextFireTime()));

        ++count;//累加count
        System.out.println("count数量：" + count);
        jobExecutionContext.getJobDetail().getJobDataMap().put("count", count);
    }
}
