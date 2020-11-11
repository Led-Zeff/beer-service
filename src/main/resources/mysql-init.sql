drop database if exists beerservice;
drop user if exists `beer_service`@`%`;
create database beerservice character set utf8mb4 collate utf8mb4_unicode_ci;
create user `beer_service`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, create, drop, references, index, alter, execute, create view, show view, create routine, alter routine, event, trigger on `beer_service`.* to `beer_service`@`%`;
flush privileges;
