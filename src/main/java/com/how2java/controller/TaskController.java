package com.how2java.controller;

import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.response.Response;
import com.how2java.service.STimetaskService;
import com.how2java.task.InitQuartzJob;
import com.how2java.task.ScheduleJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wangyi
 * @Date:2019/9/11
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private STimetaskService timetaskService;

    /**
     * 更新定时任务
     * @param timetask
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/save")
    public Response saveTask(WxTimetask timetask) throws SchedulerException {
        timetaskService.insert(timetask);
        ScheduleJob job = InitQuartzJob.buildScheduleJob(timetask);
        InitQuartzJob.addJob(job);
        return new Response();
    }

    /**
     * 暂停定时任务
     * @param timetask
     * @return
     */
    @RequestMapping("/pause")
    public Response pauseTask(WxTimetask timetask){
        return new Response();
    }

    /**
     * 移除定时任务
     * @param timetask
     * @return
     */
    @RequestMapping("/remove")
    public Response removeTask(WxTimetask timetask){
        return new Response();
    }
}
