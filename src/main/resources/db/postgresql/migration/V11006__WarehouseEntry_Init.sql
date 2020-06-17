DROP TYPE if EXISTS "warehouse_entry_status"  CASCADE;
CREATE TYPE "warehouse_entry_status" AS ENUM ('AVAILABLE', 'RECEIVED');

DROP TABLE if EXISTS "warehouse_entries"  CASCADE;

CREATE TABLE "warehouse_entries" (
    "id" INT8 NOT NULL,
    "warehouse_id" INT8 NOT NULL,
    "product_id" INT8 NOT NULL,
    "count" DECIMAL(19,2) NOT NULL,
    "status" warehouse_entry_status,
    PRIMARY KEY ("id")
);
