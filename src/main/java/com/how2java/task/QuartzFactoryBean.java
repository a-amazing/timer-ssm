package com.how2java.task;

import com.how2java.service.TestService;
import com.how2java.util.TaskUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author:wangyi
 * @Date:2019/9/9
 */
public class QuartzFactoryBean implements Job {
    @Autowired
    private TestService testService;

    public final Logger log = LoggerFactory.getLogger(this.getClass());


    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }
}
