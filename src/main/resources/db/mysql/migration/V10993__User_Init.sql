DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(512),
    `password` VARCHAR(1024) NOT NULL,
    `locked` TINYINT(1) DEFAULT 0,
    `enabled` TINYINT(1) DEFAULT 1,
    PRIMARY KEY (`id`),
    UNIQUE(email)
) engine=InnoDB;

