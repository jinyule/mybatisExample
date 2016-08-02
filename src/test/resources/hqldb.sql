drop table users if exists;

create table users (
  id int,
  name varchar(20),
  constraint pk_users primary key (id)
);

insert into users (id, name) values(1, 'User1');
