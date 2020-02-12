INSERT INTO `schedules` (`name`, `description`, `expression`) VALUES ('Once per 30 minutes', 'Once per 30 minutes', '0 */1 * * * *');
SET @schedule = LAST_INSERT_ID();
INSERT INTO `jobs` (`name`, `bean_id`, `description`) VALUES ('ProductWarehouseStatusUpdateCronJob', 'productWarehouseStatusUpdateCronJob', 'Updates products warehouse available field according to warehouse entries count');
SET @job = LAST_INSERT_ID();
INSERT INTO `cron_jobs` (`active`, `job_id`, `schedule_id`) VALUES (true, @job, @schedule);