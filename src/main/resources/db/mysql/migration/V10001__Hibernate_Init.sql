DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint
) engine=MyISAM;

INSERT INTO `hibernate_sequence` VALUES ( 1 );
