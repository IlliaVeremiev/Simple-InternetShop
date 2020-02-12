DROP TABLE if EXISTS `order_entries`;

CREATE TABLE `order_entries` (
    `id` BIGINT NOT NULL,
    `order_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `price` DECIMAL(19,2) NOT NULL,
    `quantity` DECIMAL(19,2) NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `order_entries` ADD CONSTRAINT OrderEntry_Order_FK
FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `order_entries` ADD CONSTRAINT OrderEntry_Product_FK
FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

