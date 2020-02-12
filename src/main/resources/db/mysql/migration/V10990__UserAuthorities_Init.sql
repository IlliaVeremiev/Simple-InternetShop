DROP TABLE IF EXISTS `users_authorities`;

CREATE TABLE `users_authorities` (
    `user_id` BIGINT NOT NULL,
    `authority_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `authority_id`)
) engine=InnoDB;

