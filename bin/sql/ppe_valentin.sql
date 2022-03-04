drop database if exists ppe_valentin; 
create database ppe_valentin;
	use ppe_valentin


create table user (
	iduser int(3) not null auto_increment,
	nom varchar(30),
	prenom varchar(30),
	email varchar(100),
	mdp varchar(255),
	role enum("admin","user"),
	primary key (iduser)
);

insert into user values (null,"Puillandre", "Valentin", "a@gmail.com","123", "admin"), (null,"KADDOURI", "Yanis", "b@gmail.com","456", "user");
