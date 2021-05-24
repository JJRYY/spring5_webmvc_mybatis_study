drop user if exists 'user_spring5_mybatis';
grant all privileges
	on spring5_mybatis.*
	to 'user_spring5_mybatis'@'localhost' identified by 'rootroot';

drop database if exists spring5_mybatis;
create database spring5_mybatis;