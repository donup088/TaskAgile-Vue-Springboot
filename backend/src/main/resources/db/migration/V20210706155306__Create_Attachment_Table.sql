DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `file_name`  VARCHAR(255) NOT NULL,
    `file_path`  VARCHAR(255),
    `card_id`    BIGINT       NOT NULL,
    `created_at` DATETIME     NOT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    `deleted_at` DATETIME DEFAULT NULL,
    `created_by` BIGINT   DEFAULT NULL,
    `updated_by` BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);