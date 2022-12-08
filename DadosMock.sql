drop table test;

create table test (
id		INT							not null	auto_increment	,
name	VARCHAR(255)				not null					,
age		INT(3)						not null					,
email	VARCHAR(255)				not null					,
status	ENUM('ATIVO', 'INATIVO')	not null					,
primary key (id)
);

insert into test (name, age, email, status) values ('Abner Amos', 29, 'abner@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Juliana Menezes', 25, 'juhh@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Anna Beatriz', 9, 'anna@email.com', 'INATIVO')
insert into test (name, age, email, status) values ('Alicia Silva', 3, 'alicia@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Lara Silva', 1, 'lara@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Livia Silva', 1, 'livia@email.com', 'INATIVO')
insert into test (name, age, email, status) values ('Marcia Silva', 50, 'marcia@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Reginaldo Olivar', 60, 'regis@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Valter Souza', 65, 'valter@email.com', 'INATIVO')
insert into test (name, age, email, status) values ('Sebastiana Aparecida', 52, 'tiana@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Valter Junior', 40, 'junior@email.com', 'ATIVO')
insert into test (name, age, email, status) values ('Erik Gonzaga', 35, 'erik@email.com', 'INATIVO')

select * from test;
select * from test where age >= 50
select * from test where age >= 50 and status = 'ATIVO';