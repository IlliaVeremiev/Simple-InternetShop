package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.CronJob;
import veil.internetshop.simple.models.Schedule;

import java.util.List;

public interface CronJobRepository extends JpaRepository<CronJob, Long>{

	/**
	 * Returns all active cron jobs related with <b>schedule</b>
	 *
	 * @param schedule - related schedule
	 * @return all cron jobs related with <b>schedule</b>
	 */
	List<CronJob> findAllByScheduleAndActiveTrue(Schedule schedule);

}
