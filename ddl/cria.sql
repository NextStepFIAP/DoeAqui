create table role (id number(19,0) not null, name varchar2(255 char), primary key (id))
create table tb_doe_user (cd_usuario number(19,0) not null, ds_user varchar2(255 char), ds_email varchar2(128 char) not null, nm_usuario varchar2(64 char) not null, nm_senha varchar2(100 char) not null, primary key (cd_usuario))
create table tb_doe_user_roles (user_cd_usuario number(19,0) not null, roles_id number(19,0) not null)
alter table tb_doe_user drop constraint UK_j3dhn1chvn2fu0p3ap5f8bj86
alter table tb_doe_user add constraint UK_j3dhn1chvn2fu0p3ap5f8bj86 unique (ds_email)
create sequence hibernate_sequence start with 1 increment by  1
create sequence sq_tb_doe_usuario start with 1 increment by  1
alter table tb_doe_user_roles add constraint FK4qfqko6bro70yvx9e8cchw8ls foreign key (roles_id) references role
alter table tb_doe_user_roles add constraint FK2ki7el1q4b7x4sjhy4lqwicjv foreign key (user_cd_usuario) references tb_doe_user