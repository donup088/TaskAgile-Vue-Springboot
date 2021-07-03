DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                       BIGINT       NOT NULL AUTO_INCREMENT,
    `kakao_id`                 BIGINT,
    `email`                    VARCHAR(100) ,
    `name`                     VARCHAR(100) NOT NULL,
    `password`                 VARCHAR(150),
    `provider`                 VARCHAR(50)  NOT NULL,
    `role`                     VARCHAR(50)  NOT NULL,
    `refresh_token`            VARCHAR(255) NOT NULL,
    `refresh_token_expired_at` DATETIME     NOT NULL,
    `created_at`               DATETIME     NOT NULL,
    `updated_at`               DATETIME DEFAULT NULL,
    `deleted_at`               DATETIME DEFAULT NULL,
    `created_by`               BIGINT   DEFAULT NULL,
    `updated_by`               BIGINT   DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE INDEX `email_index` ON `user` (`email`);