DROP TABLE IF EXISTS "cron_jobs" CASCADE;

CREATE TABLE "cron_jobs" (
    "id" INT8 NOT NULL,
    "active" BOOLEAN NOT NULL,
    "job_id" INT8 NOT NULL,
    "schedule_id" INT8 NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "cron_jobs" ADD CONSTRAINT CronJob_Job_FK
FOREIGN KEY ("job_id") REFERENCES "jobs" ("id");

ALTER TABLE "cron_jobs" ADD CONSTRAINT CronJob_Schedule_FK
FOREIGN KEY ("schedule_id") REFERENCES "schedules" ("id");
