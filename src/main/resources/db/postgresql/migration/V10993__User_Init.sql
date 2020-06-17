DROP TABLE IF EXISTS "users" CASCADE;

CREATE TABLE "users" (
    "id" INT8 NOT NULL,
    "email" VARCHAR(512),
    "password" VARCHAR(1024) NOT NULL,
    "locked" BOOLEAN DEFAULT FALSE,
    "enabled" BOOLEAN DEFAULT TRUE,
    PRIMARY KEY ("id"),
    UNIQUE(email)
);

