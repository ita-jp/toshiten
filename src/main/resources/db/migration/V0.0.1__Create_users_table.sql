CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(68) NOT NULL, -- bcrypt password length is 68 chars
    enabled  BOOLEAN     NOT NULL DEFAULT TRUE
);
