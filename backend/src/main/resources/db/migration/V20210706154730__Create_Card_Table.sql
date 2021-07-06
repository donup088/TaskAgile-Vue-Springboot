DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(255) NOT NULL,
    `description`  VARCHAR(255),
    `position`     INT(11)      NOT NULL,
    `card_list_id` BIGINT       NOT NULL,
    `created_at`   DATETIME     NOT NULL,
    `updated_at`   DATETIME DEFAULT NULL,
    `deleted_at`   DATETIME DEFAULT NULL,
    `created_by`   BIGINT   DEFAULT NULL,
    `updated_by`   BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);