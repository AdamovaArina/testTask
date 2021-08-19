
drop schema if exists people cascade;
create schema people;

create type gender as enum('MALE', 'FEMALE');

create table people.tperson
(
	person_id serial PRIMARY KEY,
	last_name character varying(20) not null,
	first_name character varying(20) not null,
	patronymic character varying(20) not null,
	date_of_birth date not null,
	person_gender gender not null
);

insert into people.tperson values(default,'Иванов', 'Иван', 'Иванович', date '1.01.2000', 'MALE'); 
insert into people.tperson values(default,'Петров', 'Петр', 'Петрович', date '2.01.1990', 'MALE'); 
insert into people.tperson values(default,'Александрова', 'Александра', 'Александровна', date '1.09.2002', 'FEMALE'); 