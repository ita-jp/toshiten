START TRANSACTION;

SELECT id
INTO @role_id_system_admin
FROM roles
WHERE name = 'SystemAdmin'
;
SELECT id
INTO @role_id_system_operator
FROM roles
WHERE name = 'SystemOperator'
;

INSERT INTO permissions (name)
VALUES ('Shop:Create')
;

SELECT id
INTO @permission_id_shop_crate
FROM permissions
WHERE name = 'Shop:Create'
;

INSERT INTO role_permissions (role_id, permission_id)
VALUES (@role_id_system_admin, @permission_id_shop_crate)
     , (@role_id_system_operator, @permission_id_shop_crate)
;

COMMIT;
