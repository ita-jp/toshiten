CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS permissions
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS role_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

INSERT INTO roles (id, name)
VALUES (1, 'SystemAdmin')
     , (2, 'SystemOperator')
     , (3, 'ShopManager')
     , (4, 'ShopCast')
     , (5, 'MemberCustomer')
     , (6, 'GuestCustomer')
;

INSERT INTO permissions (id, name)
VALUES (1, 'AccessControl:Read')
     , (2, 'AccessControl:Write')
;

-- role_id=1: SystemAdmin
INSERT INTO role_permissions (role_id, permission_id)
VALUES (1, 1)
     , (1, 2)
;

-- password = rootpass
INSERT INTO users (id, username, password)
VALUES (1, 'root', '{bcrypt}$2a$10$hSPRcBpvCw5Txk/xm8Zmre1xTCZjhh/GsfEyCPTwZrKrKa4pQ7d12');
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);
