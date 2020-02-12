DROP TABLE if EXISTS `carts`;

CREATE TABLE `carts` (
    `id` BIGINT NOT NULL,
    `user_id` BIGINT,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `carts` ADD CONSTRAINT Cart_User_FK
FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
