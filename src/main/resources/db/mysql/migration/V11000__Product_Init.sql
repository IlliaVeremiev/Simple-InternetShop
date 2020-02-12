DROP TABLE if EXISTS `products`;

CREATE TABLE `products` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(256) NOT NULL,
    `price` DECIMAL(19,2) NOT NULL,
    `quantity` DECIMAL(19,2) NOT NULL,
    `catalog` BIGINT NOT NULL,
    `category` BIGINT NOT NULL,
    `primary_photo` BIGINT,

    `_warehouse_available_` BIT NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `products` ADD CONSTRAINT Products_Catalog_FK 
FOREIGN KEY (`catalog`) REFERENCES `catalogs` (`id`);

ALTER TABLE `products` ADD CONSTRAINT Products_Category_FK 
FOREIGN KEY (`category`) REFERENCES `categories` (`id`);

ALTER TABLE `products` ADD CONSTRAINT Products_PrimaryPhoto_FK 
FOREIGN KEY (`primary_photo`) REFERENCES `medias` (`id`);


