drop table client;
drop table dependents;

create table client (
id		INT							not null	auto_increment	,
name	VARCHAR(255)				not null					,
age		INT(3)						not null					,
email	VARCHAR(255)				not null					,
status	ENUM('ATIVO', 'INATIVO')	not null					,
primary key (id)
);

insert into client (name, age, email, status) values ('Abner Amos', 29, 'abner@email.com', 'ATIVO')
insert into client (name, age, email, status) values ('Juliana Menezes', 25, 'juhh@email.com', 'ATIVO')
insert into client (name, age, email, status) values ('Marcia Silva', 50, 'marcia@email.com', 'INATIVO')
insert into client (name, age, email, status) values ('Reginaldo Olivar', 60, 'regis@email.com', 'ATIVO')
insert into client (name, age, email, status) values ('Valter Junior', 40, 'junior@email.com', 'INATIVO')
insert into client (name, age, email, status) values ('Erik Gonzaga', 35, 'erik@email.com', 'ATIVO')

select * from client;
select * from client where age >= 50
select * from client where age >= 50 and status = 'ATIVO';

create table dependents (
id				INT						not null	auto_increment	,
name_dp			VARCHAR(255)			not null					,
age_dp			INT(3)					not null					,
client_id		INT						not null					,
primary key (id)													,
foreign key (client_id) references client (id)
);

insert into dependents (name_dp, age_dp, client_id) values ('Anna Beatriz', 9, 2)
insert into dependents (name_dp, age_dp, client_id) values ('Alicia Silva', 3, 1)
insert into dependents (name_dp, age_dp, client_id) values ('Lara Silva', 1, 1)
insert into dependents (name_dp, age_dp, client_id) values ('Livia Silva', 1, 1)
insert into dependents (name_dp, age_dp, client_id) values ('Valter Souza', 65, 6)
insert into dependents (name_dp, age_dp, client_id) values ('Sebastiana Aparecida', 52, 6)

select * from dependents;
select * from dependents d inner join client c on (d.client_id = c.id)
where c.name = 'Abner Amos';

ALTER TABLE dependents 
CHANGE COLUMN responsible_id client_id INT NOT NULL;