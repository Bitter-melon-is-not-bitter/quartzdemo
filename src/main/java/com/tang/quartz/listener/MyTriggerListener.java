package com.tang.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class MyTriggerListener implements TriggerListener {
    public String getName() {
        String simpleName = this.getClass().getSimpleName();
        System.out.println("触发器的名称：" + simpleName);
        return simpleName;
    }

    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
        System.out.println(name + "被触发");
    }

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String name = trigger.getKey().getName();
        System.out.println(name + "没有被触发");
        return false;//true表示不会执行Job的方法 false反之
    }

    public void triggerMisfired(Trigger trigger) {
        String name = trigger.getKey().getName();
        System.out.println(name + "错过触发");
    }

    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        String name = trigger.getKey().getName();
        System.out.println(name + "完成之后触发");
    }
}
