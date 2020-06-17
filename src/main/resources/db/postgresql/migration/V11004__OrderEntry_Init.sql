DROP TABLE if EXISTS "order_entries" CASCADE;

CREATE TABLE "order_entries" (
    "id" INT8 NOT NULL,
    "order_id" INT8 NOT NULL,
    "product_id" INT8 NOT NULL,
    "price" DECIMAL(19,2) NOT NULL,
    "quantity" DECIMAL(19,2) NOT NULL,
    PRIMARY KEY ("id")
);

ALTER TABLE "order_entries" ADD CONSTRAINT OrderEntry_Order_FK
FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_entries" ADD CONSTRAINT OrderEntry_Product_FK
FOREIGN KEY ("product_id") REFERENCES "products" ("id");

