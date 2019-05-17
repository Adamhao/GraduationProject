--liquibase formatted sql


--changeset brook:1
ALTER TABLE t_user add email VARCHAR(30);

--changeset brook:2
ALTER TABLE t_user add bank BIGINT;

--changeset brook:3
ALTER TABLE t_product ADD url VARCHAR(255);

--changeset brook:4
ALTER TABLE t_product DROP code;
ALTER TABLE t_picture DROP updateAdmin_id;

