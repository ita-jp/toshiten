dump-ddl:
	docker exec -i -e MYSQL_PWD='example' toshiten-db-1 mysqldump -u root --no-data --skip-add-drop-table ourdb | sed '/^\/\*![0-9]* /d' | tee ./build/schema.sql