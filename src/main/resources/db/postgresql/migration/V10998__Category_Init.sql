DROP TABLE IF EXISTS "categories" CASCADE;

CREATE TABLE "categories" (
    "id" INT8 NOT NULL,
    "code" VARCHAR(128) NOT NULL,
    "name" VARCHAR(256) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "categories" ADD CONSTRAINT Category_Code_UQ UNIQUE ("code");

--ALTER TABLE "categories" ADD CONSTRAINT Category_Parent_FK
--FOREIGN KEY ("parent") REFERENCES "categories" ("id");

