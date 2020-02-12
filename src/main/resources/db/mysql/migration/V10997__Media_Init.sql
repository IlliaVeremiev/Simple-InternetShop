DROP TABLE IF EXISTS `medias`;

CREATE TABLE `medias` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `owner` BIGINT,
    `path` VARCHAR(256) NOT NULL,
    `type` VARCHAR(128) NOT NULL,
    `upload_date` DATETIME, 
    PRIMARY KEY (`id`)
) engine=InnoDB;
