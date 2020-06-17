DROP TABLE IF EXISTS "authorities" CASCADE;

CREATE TABLE "authorities" (
    "id" INT8 NOT NULL,
    "value" VARCHAR(512) NOT NULL UNIQUE,
    PRIMARY KEY (id)
)