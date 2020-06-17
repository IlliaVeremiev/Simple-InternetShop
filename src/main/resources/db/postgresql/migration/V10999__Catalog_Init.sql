DROP TABLE if EXISTS "catalogs" CASCADE;

CREATE TABLE "catalogs" (
    "id" INT8 NOT NULL,
    "active" BOOLEAN NOT NULL,
    "name" VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

