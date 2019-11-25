CREATE TABLE `acg_order`
(
    `id`      BIGINT(25) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `user_id` BIGINT(25)                NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;
