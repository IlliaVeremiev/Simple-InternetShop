package veil.internetshop.simple.services;

import veil.internetshop.simple.models.CronJob;
import veil.internetshop.simple.models.Schedule;

import java.util.List;

public interface CronJobService{

	List<CronJob> findActiveCronsBySchedule(Schedule schedule);

	CronJob save(CronJob cronJob);
}
