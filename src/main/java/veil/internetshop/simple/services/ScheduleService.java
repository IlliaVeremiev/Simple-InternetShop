package veil.internetshop.simple.services;

import veil.internetshop.simple.models.CronJob;

public interface ScheduleService{

    void runCronJob(CronJob cronJob);
}
