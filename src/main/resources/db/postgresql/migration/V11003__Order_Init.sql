DROP TABLE if EXISTS "orders" CASCADE;

CREATE TABLE "orders" (
    "id" INT8 NOT NULL,
    "user_id" INT8 NOT NULL,
    "date" TIMESTAMP NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "orders" ADD CONSTRAINT Order_User_FK
FOREIGN KEY ("user_id") REFERENCES "users" ("id");
