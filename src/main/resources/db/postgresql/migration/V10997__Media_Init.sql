DROP TABLE IF EXISTS "medias" CASCADE;

CREATE TABLE "medias" (
    "id" INT8 NOT NULL,
    "owner" INT8,
    "path" VARCHAR(256) NOT NULL,
    "type" VARCHAR(128) NOT NULL,
    "upload_date" TIMESTAMP,
    PRIMARY KEY ("id")
);
