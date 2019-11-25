CREATE TABLE `user`
(
    `id`        BIGINT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    `username`  VARCHAR(25)               NOT NULL,
    `password`  VARCHAR(255)              NOT NULL,
    `email`     VARCHAR(25)               NOT NULL,
    `cell_phone` BIGINT(11)                NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;
