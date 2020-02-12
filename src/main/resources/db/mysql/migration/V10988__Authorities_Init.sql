DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `value` VARCHAR(512) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE(value)
) engine=InnoDB;

