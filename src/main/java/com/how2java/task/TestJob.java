package com.how2java.task;

import com.how2java.service.TestService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author:wangyi
 * @Date:2019/9/11
 */
public class TestJob implements Job {
    @Autowired
    private TestService testService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        testService.print();
    }
}
