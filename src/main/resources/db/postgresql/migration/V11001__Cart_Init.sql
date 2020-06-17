DROP TABLE if EXISTS "carts" CASCADE;

CREATE TABLE "carts" (
    "id" INT8 NOT NULL,
    "user_id" INT8,
    PRIMARY KEY ("id")
);

ALTER TABLE "carts" ADD CONSTRAINT Cart_User_FK
FOREIGN KEY ("user_id") REFERENCES "users" ("id");
