DROP TABLE if EXISTS `warehouses`;

CREATE TABLE `warehouses` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(256) NOT NULL,
    `location` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;
