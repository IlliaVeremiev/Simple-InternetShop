DROP TABLE if EXISTS `orders`;

CREATE TABLE `orders` (
    `id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `orders` ADD CONSTRAINT Order_User_FK
FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
