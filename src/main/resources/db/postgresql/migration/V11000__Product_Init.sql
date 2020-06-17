DROP TABLE if EXISTS "products" CASCADE;

CREATE TABLE "products" (
    "id" INT8 NOT NULL,
    "name" VARCHAR(256) NOT NULL,
    "price" DECIMAL(19,2) NOT NULL,
    "quantity" DECIMAL(19,2) NOT NULL,
    "catalog" int8 NOT NULL,
    "category" int8 NOT NULL,
    "primary_photo" INT8,
    "_warehouse_available_" BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY ("id")
);

ALTER TABLE "products" ADD CONSTRAINT Products_Catalog_FK 
FOREIGN KEY ("catalog") REFERENCES "catalogs" ("id");

ALTER TABLE "products" ADD CONSTRAINT Products_Category_FK 
FOREIGN KEY ("category") REFERENCES "categories" ("id");

ALTER TABLE "products" ADD CONSTRAINT Products_PrimaryPhoto_FK 
FOREIGN KEY ("primary_photo") REFERENCES "medias" ("id");


