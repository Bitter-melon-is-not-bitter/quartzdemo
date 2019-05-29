package com.tang.quartz.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorld implements Job {//使用quartz的实现Job这个类

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("正在进行数据库的的备份" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
