CREATE TABLE cast_images
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    cast_id       BIGINT NOT NULL,
    path          VARCHAR(255),
    display_order INT    NOT NULL,
    FOREIGN KEY (cast_id) REFERENCES casts (id)
);
