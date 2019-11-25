CREATE TABLE `acg_goods`
(
    `id`           BIGINT(25) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `acg_order_id` BIGINT(25)                NOT NULL,
    `name`         VARCHAR(36)               NOT NULL,
    `price`        DECIMAL                   NOT NULL,
    `quantity`     INT                       NOT NULL,
    CONSTRAINT `fk_goods_order` FOREIGN KEY (`acg_order_id`) REFERENCES `acg_order` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;
