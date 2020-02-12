package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.models.CronJob;
import veil.internetshop.simple.models.Schedule;
import veil.internetshop.simple.repositories.CronJobRepository;
import veil.internetshop.simple.services.CronJobService;

import java.util.List;

public class DefaultCronJobService implements CronJobService{

    @Autowired
    private CronJobRepository cronJobRepository;

    @Override
    public List<CronJob> findActiveCronsBySchedule(Schedule schedule){
        return cronJobRepository.findAllByScheduleAndActiveTrue(schedule);
    }

    @Override
    public CronJob save(CronJob cronJob){
        return cronJobRepository.save(cronJob);
    }
}
