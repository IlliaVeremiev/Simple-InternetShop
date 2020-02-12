DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `bean_id` VARCHAR(128) NOT NULL,
    `description` VARCHAR(2048), 
    `name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

