DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
    `id` BIGINT NOT NULL AUTO_INCREMENT, 
    `code` VARCHAR(128) NOT NULL,
    `name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`id`)
) engine=InnoDB;

ALTER TABLE `categories` ADD CONSTRAINT Category_Code_UQ UNIQUE (`code`);

--ALTER TABLE `categories` ADD CONSTRAINT Category_Parent_FK
--FOREIGN KEY (`parent`) REFERENCES `categories` (`id`);

