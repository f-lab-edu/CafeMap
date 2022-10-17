DROP TABLE IF EXISTS USER;

CREATE TABLE `user`
(
    id           int         NOT NULL AUTO_INCREMENT,
    login_id     varchar(50) NOT NULL,
    name         varchar(50) NOT NULL,
    password     varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    created_at   timestamp   NOT NULL,
    modified_at  timestamp NULL DEFAULT NULL,
    created_by   varchar(50) NOT NULL,
    modified_by  varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
);