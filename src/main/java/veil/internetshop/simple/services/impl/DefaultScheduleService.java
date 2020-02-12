package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import veil.internetshop.simple.models.CronJob;
import veil.internetshop.simple.repositories.ScheduleRepository;
import veil.internetshop.simple.services.CronJobService;
import veil.internetshop.simple.services.ScheduleService;

import java.util.Collection;

public class DefaultScheduleService implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private CronJobService cronJobService;

    public void setUp(){
        initCronJobs();
    }

    @Override
    public void runCronJob(CronJob cronJob){
        String beanName = cronJob.getJob().getBeanId();
        Runnable task = appContext.getBean(beanName, Runnable.class);
        taskScheduler.schedule(task, new CronTrigger(cronJob.getSchedule().getExpression()));
    }

    private void initCronJobs(){
        scheduleRepository.findAll()
                .stream()
                .map(cronJobService::findActiveCronsBySchedule)
                .flatMap(Collection::stream)
                .forEach(this::runCronJob);
    }
}
