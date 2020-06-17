DROP TABLE if EXISTS "cart_entries" CASCADE;

CREATE TABLE "cart_entries" (
    "id" INT8 NOT NULL,
    "cart_id" INT8 NOT NULL,
    "product_id" INT8 NOT NULL,
    "count" DECIMAL(19,2) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "cart_entries" ADD CONSTRAINT CartEntry_Cart_FK
FOREIGN KEY ("cart_id") REFERENCES "carts" ("id");

ALTER TABLE "cart_entries" ADD CONSTRAINT CartEntry_Product_FK
FOREIGN KEY ("product_id") REFERENCES "products" ("id");

