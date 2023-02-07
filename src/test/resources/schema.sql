DROP TABLE IF EXISTS user;

CREATE TABLE user
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

CREATE TABLE user_address
(
    id             int         NOT NULL AUTO_INCREMENT,
    login_id       varchar(50) NOT NULL,
    street_address varchar(50) NOT NULL,
    detail_address varchar(50) NOT NULL,
    latitude       varchar(50) NOT NULL,
    longitude      varchar(50) NOT NULL,
    created_at     timestamp   NOT NULL,
    modified_at    timestamp NULL DEFAULT NULL,
    created_by     varchar(50) NOT NULL,
    modified_by    varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE cafe
(
    id         int            NOT NULL,
    cafe_id    int            NOT NULL,
    name       varchar(50)    NOT NULL,
    latitude   decimal(10, 0) NOT NULL,
    longitude  decimal(10, 0) NOT NULL,
    user_id    int            NOT NULL,
    created_at timestamp      NOT NULL,
    PRIMARY KEY (`id`)
);