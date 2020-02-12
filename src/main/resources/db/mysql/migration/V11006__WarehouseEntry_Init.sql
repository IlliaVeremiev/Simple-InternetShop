DROP TABLE if EXISTS `warehouse_entries`;

CREATE TABLE `warehouse_entries` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `warehouse_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `count` DECIMAL(19,2) NOT NULL,
    `status` ENUM('AVAILABLE', 'RECEIVED'),
    PRIMARY KEY (`id`)
) engine=InnoDB;

DROP PROCEDURE if EXISTS `product_warehouse_status_update_cron_job`;

DELIMITER $$
CREATE PROCEDURE `product_warehouse_status_update_cron_job`()
BEGIN
	DECLARE done INT DEFAULT FALSE;
    DECLARE productId BIGINT;
    DECLARE warehouseAvailable BIT;
    DECLARE count BIGINT;
    DECLARE ProductCursor CURSOR FOR
		SELECT `p`.`id`, `p`.`_warehouse_available_`, sum(`we`.`count` > 0 AND `we`.`status` = "AVAILABLE")
		FROM `products` `p` LEFT OUTER JOIN `warehouse_entries` `we` ON `we`.`product_id` = `p`.`id`
		GROUP BY `p`.`id`;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN ProductCursor;

    productsLoop : LOOP
		FETCH NEXT FROM ProductCursor INTO productId, warehouseAvailable, count;
		IF done THEN LEAVE productsLoop;
        END IF;

        IF warehouseAvailable AND count = 0 THEN
			UPDATE `products` SET `_warehouse_available_` = false WHERE `id` = productId;
		END IF;
		IF !warehouseAvailable AND count > 0 THEN
			UPDATE `products` SET `_warehouse_available_` = true WHERE `id` = productId;
		END IF;
    END LOOP;

    CLOSE ProductCursor;
END $$
DELIMITER ;