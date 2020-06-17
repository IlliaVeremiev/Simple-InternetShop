DROP TABLE if EXISTS "warehouses" CASCADE;

CREATE TABLE "warehouses" (
    "id" BIGINT NOT NULL,
    "name" VARCHAR(256) NOT NULL,
    "location" INT8 NOT NULL,
    PRIMARY KEY ("id")
);
