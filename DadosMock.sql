drop table test;

create table test (
id		INT				not null	auto_increment	,
name	VARCHAR(255)	not null					,
age		INT(3)			not null					,
primary key (id)
);

insert into test (name, age) values ('Abner Amos', 29)
insert into test (name, age) values ('Juliana Menezes', 25)
insert into test (name, age) values ('Anna Beatriz', 9)
insert into test (name, age) values ('Alicia Silva', 3)
insert into test (name, age) values ('Lara Silva', 1)
insert into test (name, age) values ('Livia Silva', 1)
insert into test (name, age) values ('Marcia Silva', 50)
insert into test (name, age) values ('Reginaldo Olivar', 60)
insert into test (name, age) values ('Valter Souza', 65)
insert into test (name, age) values ('Sebastiana Aparecida', 52)
insert into test (name, age) values ('Valter Junior', 40)
insert into test (name, age) values ('Erik Gonzaga', 35)

select * from test;
select name from test where age >= 50;