DELETE from users;

-- password = password1
INSERT INTO users (id, username, password, enabled) VALUES(1, 'user1', '{bcrypt}$2a$10$V5.eihv7khLDkNcz8moUgewodj4BgkA66DsB.LGsWOvbGh4Cg5YIq', true);