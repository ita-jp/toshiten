DELETE FROM users;

-- password = password1
INSERT INTO users (id, username, password, enabled) VALUES(1, 'user1', '{bcrypt}$2a$10$V5.eihv7khLDkNcz8moUgewodj4BgkA66DsB.LGsWOvbGh4Cg5YIq', true);

DELETE FROM casts;
DELETE FROM shops;

INSERT INTO shops (id, name) VALUES(1, 'ショップ1');
INSERT INTO shops (id, name) VALUES(2, 'ショップ2');
INSERT INTO shops (id, name) VALUES(3, 'ショップ3');

INSERT INTO casts (id, shop_id, name) VALUES(1, 1, 'キャスト1-1');
INSERT INTO casts (id, shop_id, name) VALUES(2, 1, 'キャスト1-2');
INSERT INTO casts (id, shop_id, name) VALUES(3, 1, 'キャスト1-3');

INSERT INTO casts (id, shop_id, name) VALUES(4, 2, 'キャスト2-1');
INSERT INTO casts (id, shop_id, name) VALUES(5, 2, 'キャスト2-2');
INSERT INTO casts (id, shop_id, name) VALUES(6, 2, 'キャスト2-3');

INSERT INTO casts (id, shop_id, name) VALUES(7, 3, 'キャスト3-1');
INSERT INTO casts (id, shop_id, name) VALUES(8, 3, 'キャスト3-2');
INSERT INTO casts (id, shop_id, name) VALUES(9, 3, 'キャスト3-3');