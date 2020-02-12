DROP TABLE IF EXISTS `schedules`;

CREATE TABLE `schedules` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `description` VARCHAR(2048), 
    `expression` VARCHAR(256) NOT NULL,
    `name` VARCHAR(256), 
    PRIMARY KEY (`id`)
) engine=InnoDB;
