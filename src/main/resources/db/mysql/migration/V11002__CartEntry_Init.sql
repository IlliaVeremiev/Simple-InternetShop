DROP TABLE if EXISTS `cart_entries`;

CREATE TABLE `cart_entries` (
    `id` BIGINT NOT NULL,
    `cart_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `count` DECIMAL(19,2) NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `cart_entries` ADD UNIQUE `unique_index`(`cart_id`, `product_id`);

ALTER TABLE `cart_entries` ADD CONSTRAINT CartEntry_Cart_FK
FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`);

ALTER TABLE `cart_entries` ADD CONSTRAINT CartEntry_Product_FK
FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

