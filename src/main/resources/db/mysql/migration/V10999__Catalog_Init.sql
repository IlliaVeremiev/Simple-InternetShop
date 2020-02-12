DROP TABLE if EXISTS `catalogs`;

CREATE TABLE `catalogs` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `active` BIT NOT NULL, 
    `name` VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
) engine=InnoDB;

