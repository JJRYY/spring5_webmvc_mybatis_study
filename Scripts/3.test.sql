select user(), database();

select * from member;

insert into member(email, password, name) values ('test@test.com', 1234, 'test');

desc member;

select * from member;
select count(*) from member;

delete from member where id > 6;

alter table member auto_increment=5;