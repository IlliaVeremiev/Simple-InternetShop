DROP TABLE IF EXISTS `cron_jobs`;

CREATE TABLE `cron_jobs` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `active` BIT NOT NULL, 
    `job_id` BIGINT NOT NULL,
    `schedule_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `cron_jobs` ADD CONSTRAINT CronJob_Job_FK
FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`);

ALTER TABLE `cron_jobs` ADD CONSTRAINT CronJob_Schedule_FK
FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`id`);
