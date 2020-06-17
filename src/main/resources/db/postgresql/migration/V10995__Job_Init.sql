DROP TABLE IF EXISTS "jobs" CASCADE;

CREATE TABLE "jobs" (
    "id" INT8 NOT NULL,
    "bean_id" VARCHAR(128) NOT NULL,
    "description" VARCHAR(2048), 
    "name" VARCHAR(256) NOT NULL,
    PRIMARY KEY ("id")
);

