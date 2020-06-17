DROP TABLE IF EXISTS "users_authorities" CASCADE;

CREATE TABLE "users_authorities" (
    "user_id" INT8 NOT NULL,
    "authority_id" INT8 NOT NULL,
    PRIMARY KEY ("user_id", "authority_id")
);

