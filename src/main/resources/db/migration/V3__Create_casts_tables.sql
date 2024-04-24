CREATE TABLE casts
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(256) NOT NULL,
    age          INT          NOT NULL,
    height       INT          NOT NULL,
    size_bust    INT          NOT NULL,
    size_waist   INT          NOT NULL,
    size_hip     INT          NOT NULL,
    zodiac_sign  VARCHAR(256) NOT NULL,
    blood_type   VARCHAR(256) NOT NULL,
    cast_comment TEXT,
    shop_comment TEXT,
    shop_id      BIGINT       NOT NULL REFERENCES shops (id),
    enabled      BOOLEAN      NOT NULL DEFAULT TRUE
);