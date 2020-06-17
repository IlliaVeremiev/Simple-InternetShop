DROP TABLE IF EXISTS "schedules" CASCADE;

CREATE TABLE "schedules" (
    "id" INT8 NOT NULL,
    "description" VARCHAR(2048), 
    "expression" VARCHAR(256) NOT NULL,
    "name" VARCHAR(256), 
    PRIMARY KEY ("id")
);
