package com.how2java.task;

import java.util.ArrayList;
import java.util.List;

import com.how2java.pojo.WxTimetask;
import com.how2java.pojo.WxTimetaskExample;
import com.how2java.service.STimetaskService;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * 根据上下文获取spring类
 *
 * @author
 */
public class InitQuartzJob implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(InitQuartzJob.class);

    private static ApplicationContext appCtx;
    public static SchedulerFactoryBean schedulerFactoryBean = null;

    public static void init() {
        schedulerFactoryBean = (SchedulerFactoryBean) appCtx.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            logger.info(scheduler.getSchedulerName());
        } catch (SchedulerException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 这里从数据库中获取任务信息数据
        STimetaskService sTimetaskService = (STimetaskService) appCtx.getBean(STimetaskService.class);
        WxTimetaskExample example = new WxTimetaskExample();
        WxTimetaskExample.Criteria c = example.createCriteria();
        c.andJobStatusEqualTo("1"); // 已发布的定时任务
        List<WxTimetask> list = sTimetaskService.selectByExample(example);
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (WxTimetask sTimetask : list) {
            ScheduleJob job1 = buildScheduleJob(sTimetask);
            if (job1 != null) {
                jobList.add(job1);
            }
        }

        for (
                ScheduleJob job : jobList) {
            try {
                addJob(job);
            } catch (SchedulerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    public static void addJob(ScheduleJob job) throws SchedulerException {
        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        logger.debug(scheduler + "...........................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class
                    : QuartzJobFactoryDisallowConcurrentExecution.class;
//      Class clazz = null;
//      try {
//        clazz = Class.forName(job.getBeanClass());
//      } catch (ClassNotFoundException e) {
//        logger.error(job.getBeanClass() + "对应的类不存在!");
//        return;
//      }
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).usingJobData("data", job.getJobData()).build();

            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = TriggerBuilder.newTrigger().withDescription(job.getJobId().toString()).withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).usingJobData("data", job.getJobData()).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    public static ScheduleJob buildScheduleJob(WxTimetask timetask) {
        ScheduleJob job1 = new ScheduleJob();
        job1.setJobId(timetask.getId());
        job1.setJobGroup(timetask.getGroupName()); // 任务组
        job1.setJobName(timetask.getName());// 任务名称
        job1.setJobStatus(timetask.getJobStatus()); // 任务发布状态
        job1.setIsConcurrent(timetask.getConcurrent() ? "1" : "0"); // 运行状态
        job1.setCronExpression(timetask.getCron());
        job1.setBeanClass(timetask.getBeanName());// 一个以所给名字注册的bean的实例
        job1.setMethodName(timetask.getMethodName());
        job1.setJobData(timetask.getJobData()); // 参数
        String parameterClasses = timetask.getParameterClasses();
        if (StringUtils.isNotBlank(parameterClasses)) {
            String[] split = parameterClasses.split(",");
            List<Class> clazzs = new ArrayList<>(split.length);
            try {
                for (String str : split) {
                    Class<?> aClass = Class.forName(str);
                    clazzs.add(aClass);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            job1.setParameterClasses(clazzs);
        } else {
            job1.setParameterClasses(new ArrayList<>());
        }
        return job1;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.appCtx == null) {
            ApplicationContext context = event.getApplicationContext();
            if (context.getParent() != null) {
                return;
            }
            this.appCtx = context;
        }
        init();
    }
}
