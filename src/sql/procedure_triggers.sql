Drop database if exists kanyu1;
create database kanyu1;
  use kanyu1

create table departement
  (
    numdepartement int(3) not null auto_increment,
    nomdepartement varchar(35) NOT NULL,
    primary key(numdepartement)
    )engine=InnoDB;


create table diplome
  (
    numd int NOT NULL auto_increment,
    typed varchar(25) NOT null,
    PRIMARY KEY(numd)
    )ENGINE=InnoDB;


create table secteur 
  (
    idsecteur int not null auto_increment,
    noms varchar(50) not null,
    primary key(IdSecteur)
    
    )engine=InnoDB;


create table user (
    id int(11) not null auto_increment,
    nom varchar(25),
    adresse varchar(50) NOT NULL,
    cp char(5) NOT NULL,
    ville varchar(40) NOT NULL,
    mail varchar(40) unique,
    mdp varchar(200),
    role enum("candidat","professionel","admin"),
    primary key (id)
) ENGINE=InnoDB;


CREATE TABLE candidat (
    id int(11) NOT NULL AUTO_INCREMENT,
    prenom varchar(25) NOT NULL,
    nom varchar(25) NOT NULL,
    adresse varchar(50) NOT NULL,
    cp char(5) NOT NULL,
    ville varchar(40) NOT NULL,
    mail varchar(40) NOT NULL unique,
    mdp varchar(200) NOT NULL,
    role enum("candidat","professionel","admin"),
    numd int not null,
    commentaire text,
    PRIMARY KEY (id),
    FOREIGN KEY(numd) REFERENCES diplome(numd)
) ENGINE=InnoDB;


CREATE TABLE professionels (
    id int(11) NOT NULL AUTO_INCREMENT,
    nom varchar(25) NOT NULL,
    adresse varchar(50) NOT NULL,
    cp char(5) NOT NULL,
    ville varchar(40) NOT NULL,
    mail varchar(40) NOT NULL unique,
    mdp varchar(200) NOT NULL,
    siret char(14) NOT NULL,
    role enum("candidat","professionel","admin"),
    PRIMARY KEY (id)
)ENGINE=InnoDB;


  create table poste
  (
    idposte int(11) Not null auto_increment,
    intituleposte varchar(25) not null,
    contratposte enum('CDD','CDI','Alternance','Stage') not null,
    idsecteur int not null,
    primary key(idposte,idsecteur),
    FOREIGN key(idsecteur) REFERENCES secteur(idsecteur)
    on delete cascade
    on update cascade
    )engine=InnoDB;


create table candidature
  (
    idcandidature int NOT NULL AUTO_INCREMENT,
    idsecteur int not null,
    id int(13) NOT NULL,
    numdepartement int(3) not null,
    idposte int(11) Not null,
    etat enum('valide','attente','annuler'),
    primary key(idcandidature,idsecteur,id,numdepartement,idposte),
    FOREIGN key(idsecteur) REFERENCES secteur(idsecteur),
    FOREIGN key(id) REFERENCES user(id),
    FOREIGN key(numdepartement) REFERENCES departement(numdepartement),
    FOREIGN key (idposte) REFERENCES poste(idposte)
    on delete cascade
    on update cascade
    )engine=InnoDB;

 
create table profil
(
   idcandidature int NOT NULL,
   primary key(idcandidature),
   FOREIGN key(idcandidature) REFERENCES candidature(idcandidature)
   on delete cascade
    on update cascade
)engine=InnoDB;


create table resetpassword
  (
    idresetpassword int(3) not null auto_increment,
    code varchar(255) NOT NULL,
    mail varchar(255),
    primary key(idresetpassword)
    )engine=InnoDB;

insert into user values (null,'Puillandre','23 chemin des fleurs',
                              '93120','La courneuve',
                              'v@gmail.com',sha1('123456789'),
                              'admin');
insert into user values (null,'Puillandre','23 chemin des fleurs',
                              '93120','La courneuve',
                              'admin@gmail.com','123456789',
                              'admin');



//=========================================TRIGGER HERITAGE auto_increment candidat ==========================================
  
 drop trigger if exists autoinsertcandidat ;
 delimiter //
 create trigger autoinsertcandidat 
 before insert on candidat 
 for each row  
 begin 
declare a,x,s int ;
 select max(id) into x from user ;
if x =0
  then 
      set  x= 1;
      else
       set new.id= x+1;  
end if;

     insert into user values(new.id,new.nom,new.adresse,new.cp,new.ville,new.mail,new.mdp,new.role); 
 
select count(*) into s 
 from professionels 
 where id=new.id ; 
  if s>0

     then signal sqlstate'45000' 
       set message_text= 'impossible ' ;
   end if;

end //
delimiter ; 

//=========================================TRIGGER HERITAGE auto_increment professionels ==========================================
  
 drop trigger if exists autoinsertprofessionels ;
 delimiter //
 create trigger autoinsertprofessionels
 before insert on professionels 
 for each row  
 begin 
declare a,x,s int ;
 select max(id) into x from user ;
if x =0
  then 
      set  x= 1;
      else
       set new.id= x+1;  
end if;

     insert into user values(new.id,new.nom,new.adresse,new.cp,new.ville,new.mail,new.mdp,new.role); 
 

select count(*) into s 
 from candidat 
 where id=new.id ; 
  if s>0

     then signal sqlstate'45000' 
       set message_text= 'impossible ' ;
   end if;

end //
delimiter ; 

//=========================================TRIGGER HERITAGE autoUpdate professionels ==========================================
  
 drop trigger if exists autoupdateprofessionels ;
 delimiter //
 create trigger autoupdateprofessionels
 before update on professionels 
 for each row  
 begin 

    update user 
    set nom=new.nom, adresse=new.adresse, cp=new.cp, ville=new.ville, mail=new.mail,mdp=new.mdp,role=new.role
where id = new.id;

end //
delimiter ; 


//=========================================TRIGGER HERITAGE autoUpdate candidat ==========================================
  
 drop trigger if exists autoupdatecandidat;
 delimiter //
 create trigger autoupdatecandidat
 before update on candidat 
 for each row  
 begin 

    update user 
    set nom=new.nom, adresse=new.adresse, cp=new.cp, ville=new.ville, mail=new.mail,mdp=new.mdp,role=new.role
where id = new.id;

end //
delimiter ;


//====================================================VUE PRINCIPALE=============================================


create or replace view vlescandidatures as
select c.prenom as 'prenomcandidat', c.nom as 'nomcandidat',c.ville as 'villecandidat',c.mail, s.noms as 'secteur', p.intituleposte, d.nomdepartement as 'departementrecherche',
c.commentaire as 'commentaire'
from candidat c, secteur s,poste p, departement d,candidature ca
where c.id = ca.id
and s.idsecteur = ca.idsecteur
and d.numdepartement = ca.numdepartement
and p.idposte = ca.idposte;


//======================================================CREATION TABLE ARCHIVAGE==========================================

drop table if exists archivecandidature;
create table archivecandidature as select *,curdate() datehisto 
from candidature
where 2 = 0;

alter table archivecandidature add primary key (idcandidature,datehisto);
alter table archivecandidature modify column idcandidature int primary key;
alter table archivecandidature modify column idcandidature int auto_increment;


//======================================================CREATION TRIGGER  ARCHIVAGE==========================================

drop trigger if exists archicandidature;
delimiter //
create trigger archicandidature
before delete on candidature
for each row
begin
if old.etat = 'attente'
  then signal sqlstate '45000'
  set message_text = 'candidature non traitee';
else
  insert into archivecandidature select *,sysdate() from candidature
  where old.idcandidature = idcandidature;
end if;
end //
delimiter ;

//======================================================CREATION TABLE ARCHIVAGE CANDIDAT==========================================

drop table if exists archivecandidat;
create table archivecandidat as select *, sysdate() datehisto
from candidat
where 2 = 0;


//======================================================CREATION TRIGGER ARCHIVAGE CANDIDAT==========================================

drop trigger if exists archicandidat;
delimiter //
create trigger archicandidat
before delete on candidat
for each row
begin
insert into archivecandidat select *, sysdate() from candidat where id = old.id;
end //
delimiter ;

//======================================================CREATION TABLE ARCHIVAGE PROFESSIONELS==========================================

drop table if exists archiveprofessionels;
create table archiveprofessionels as select *, sysdate() datehisto
from professionels
where 2 = 0;


//======================================================CREATION TRIGGER  ARCHIVAGE PROFESSIONELS==========================================

drop trigger if exists archiprofessionels;
delimiter //
create trigger archiprofessionels
before delete on professionels
for each row
begin
insert into archiveprofessionels select *, sysdate() from professionels where id = old.id;
end //
delimiter ;

<<<<<<< HEAD
//=======================================================CREATION D''EVENT POUR SUPPRESSION DEFINITIVE DES DONNEES APRES 1080 jours soit 36 mois
show processlist;
SET GLOBAL event_scheduler = ON;

-- test : update archiveuser set datehisto = '2021-01-01 14:55:23';

drop event if exists test_event_03;
CREATE EVENT test_event_03
ON SCHEDULE EVERY 24 HOUR
STARTS CURRENT_TIMESTAMP
ENDS CURRENT_TIMESTAMP + INTERVAL 1 HOUR
DO
delete from archivecandidature where datediff(curdate(),datehisto)>1080;

//=======================================================CREATION D''EVENT SUPPRESSION PRO
show processlist;
SET GLOBAL event_scheduler = ON;

-- test : update archiveuser set datehisto = '2021-01-01 14:55:23';

drop event if exists test_event_03;
CREATE EVENT test_event_03
ON SCHEDULE EVERY 24 HOUR
STARTS CURRENT_TIMESTAMP
ENDS CURRENT_TIMESTAMP + INTERVAL 1 HOUR
DO
delete from archiveprofessionels where datediff(curdate(),datehisto)>1080;

//=======================================================CREATION D''EVENT POUR SUPPRESSION DEFINITIVE DES DONNEES APRES 1080 jours soit 36 mois
show processlist;
SET GLOBAL event_scheduler = ON;

-- test : update archiveuser set datehisto = '2021-01-01 14:55:23';

drop event if exists test_event_03;
CREATE EVENT test_event_03
ON SCHEDULE EVERY 24 HOUR
STARTS CURRENT_TIMESTAMP
ENDS CURRENT_TIMESTAMP + INTERVAL 1 HOUR
DO
delete from archivecandidat where datediff(curdate(),datehisto)>1080;



//=======================================================CRETATION TRIGGER RESET MOT DE PASSE==============================
drop trigger if exists resetpass_after_insert;
delimiter //
create trigger resetpass_after_insert
after insert on resetpassword
for each row
begin

if (new.mail in (select mail from user))
then
    update user set mdp= sha1(new.code) where new.mail = mail;
end if;
end //
delimiter ;
//======================================================CREATION TRIGGER DE SUPPRESSION RGPD==========================================

-- drop trigger if exists archicandidaturergpd;
-- delimiter //
-- create trigger archicandidaturergpd
-- after insert on archivecandidature
-- for each row
-- begin
-- if(select datediff(curdate(),new.datehisto)>2 from archivecandidature)

--   delete from archivecandidature where id = new.id;
  
-- end //
-- delimiter ;

//=====================================DEPARTEMENT==================================


insert into departement values(null,'Ain');
insert into departement values(null,'Aisne');
insert into departement values(null,'Allier');
insert into departement values(null,'Alpes-de-Haute-Provence');
insert into departement values(null,'Hautes-Alpes');
insert into departement values(null,'Alpes-Maritimes');
insert into departement values(null,'Ardeche');
insert into departement values(null,'Ardennes');
insert into departement values(null,'Ariege');
insert into departement values(null,'Aube');
insert into departement values(null,'Aude');
insert into departement values(null,'Aveyron');
insert into departement values(null,'Bouches-du-Rhone');
insert into departement values(null,'Calvados');
insert into departement values(null,'Cantal');
insert into departement values(null,'Charente');
insert into departement values(null,'Charente-Maritime');
insert into departement values(null,'Cher');
insert into departement values(null,'Correze');
insert into departement values(null,'Corse-du-Sud');
insert into departement values(null,'Haute-Corse');
insert into departement values(null,"Cote-d\'Or");
insert into departement values(null,"Cotes d\'Armor");
insert into departement values(null,'Creuse');
insert into departement values(null,'Dordogne');
insert into departement values(null,'Doubs');
insert into departement values(null,'Drome');
insert into departement values(null,'Eure');
insert into departement values(null,'Eure-et-Loir');
insert into departement values(null,'Finistere');
insert into departement values(null,'Gard');
insert into departement values(null,'Haute-Garonne');
insert into departement values(null,'Gers');
insert into departement values(null,'Gironde');
insert into departement values(null,'Herault');
insert into departement values(null,'Ille-et-Vilaine');
insert into departement values(null,'Indre');
insert into departement values(null,'Indre-et-Loire');
insert into departement values(null,'Isere');
insert into departement values(null,'Jura');
insert into departement values(null,'Landes');
insert into departement values(null,'Loir-et-Cher');
insert into departement values(null,'Loire');
insert into departement values(null,'Haute-Loire');
insert into departement values(null,'Loire-Atlantique');
insert into departement values(null,'Loiret');
insert into departement values(null,'Lot');
insert into departement values(null,'Lot-et-Garonne');
insert into departement values(null,'Lozere');
insert into departement values(null,'Maine-et-Loire');
insert into departement values(null,'Manche');
insert into departement values(null,'Marne');
insert into departement values(null,'Haute-Marne');
insert into departement values(null,'Mayenne');
insert into departement values(null,'Meurthe-et-Moselle');
insert into departement values(null,'Meuse');
insert into departement values(null,'Morbihan');
insert into departement values(null,'Moselle');
insert into departement values(null,'Nievre');
insert into departement values(null,'Nord');
insert into departement values(null,'Oise');
insert into departement values(null,'Orne');
insert into departement values(null,'Pas-de-Calais');
insert into departement values(null,'Puy-de-Dome');
insert into departement values(null,'Pyrenees-Atlantiques');
insert into departement values(null,'Hautes-Pyrenees');
insert into departement values(null,'Pyrenees-Orientales');
insert into departement values(null,'Bas-Rhin');
insert into departement values(null,'Haut-Rhin');
insert into departement values(null,'Rhone');
insert into departement values(null,'Haute-Saone');
insert into departement values(null,'Saone-et-Loire');
insert into departement values(null,'Sarthe');
insert into departement values(null,'Savoie');
insert into departement values(null,'Haute-Savoie');
insert into departement values(null,'Paris');
insert into departement values(null,'Seine-Maritime');
insert into departement values(null,'Seine-et-Marne');
insert into departement values(null,'Yvelines');
insert into departement values(null,'Deux-Sevres');
insert into departement values(null,'Somme');
insert into departement values(null,'Tarn');
insert into departement values(null,'Tarn-et-Garonne');
insert into departement values(null,'Var');
insert into departement values(null,'Vaucluse');
insert into departement values(null,'Vendee');
insert into departement values(null,'Vienne');
insert into departement values(null,'Haute-Vienne');
insert into departement values(null,'Vosges');
insert into departement values(null,'Yonne');
insert into departement values(null,'Territoire de Belfort');
insert into departement values(null,'Essonne');
insert into departement values(null,'Hauts-de-Seine');
insert into departement values(null,'Seine-St-Denis');
insert into departement values(null,'Val-de-Marne');
insert into departement values(null,"Val-D\'Oise");
insert into departement values(null,'Guadeloupe');
insert into departement values(null,'Martinique');
insert into departement values(null,'Guyane');
insert into departement values(null,'La Reunion');
insert into departement values(null,'Mayotte');

//=======================================DIPLOME====================================

insert into diplome values(null,'Baccalaureat general/technologique');
insert into diplome values(null,'Brevet');
insert into diplome values(null,'Bts');
insert into diplome values(null,'Master 1');
insert into diplome values(null,'Master 2');
insert into diplome values(null,'Baccalaureat professionel');
insert into diplome values(null,'BUT/DUT');
insert into diplome values(null,'Licence');
insert into diplome values(null,'Licence professionelle');
insert into diplome values(null,'Diplome specifique');
insert into diplome values(null,'CAP');
insert into diplome values(null,'Pas de diplome');
insert into diplome values(null,'DEUG/DEUST');
insert into diplome values(null,'Doctorat');


//=============================================SECTEUR

insert into secteur values(null,"Agroalimentaire");
insert into secteur values(null,"Bois / Papier / Carton / Imprimerie");
insert into secteur values(null,"Banque / Assurance");
insert into secteur values(null,"BTP / Materiaux de construction");
insert into secteur values(null,"Chimie / Parachimie");
insert into secteur values(null,"Commerce / Negoce / Distribution");
insert into secteur values(null,"Edition / Communication / Multimedia");
insert into secteur values(null,"Electronique / Electricite");
insert into secteur values(null,"Etudes et conseils");
insert into secteur values(null,"Industrie pharmaceutique");
insert into secteur values(null,"Informatique / Telecoms");
insert into secteur values(null,"Machines et équipements / Automobile");
insert into secteur values(null,"Metallurgie / Travail du metal");
insert into secteur values(null,"Plastique / Caoutchouc");
insert into secteur values(null,"Services aux entreprises");
insert into secteur values(null,"Sport");
insert into secteur values(null,"Textile / Habillement / Chaussure");
insert into secteur values(null,"Sport");
insert into secteur values(null,"Transports / Logistique");


//===============================================CANDIDAT========================================================

insert into candidat values(null,'basil','audel','45 rue des pastilles','93120','La Courneuve','a@gmail.com',sha1('123456789'),'candidat',3,"Actuellement en recherche d\'alternance");
insert into candidat values(null,'ougnoulou','eaez','5 rue du pont','75018','Paris','b@gmail.com',sha1('zaerzg'),'candidat',4,"Actuellement en recherche d\'alternance");
insert into candidat values(null,'Jerome','kazamazoff','23 rue de la prospection','75020','Paris','c@gmail.com',sha1('fefzf'),'candidat',5,"Actuellement en recherche d\'alternance");
insert into candidat values(null,'Jacques','decompostel','24 avenue des noyers','93000','Bobigny','d@gmail.com',sha1('vefefg9'),'candidat',1,"Actuellement en recherche d\'alternance");
insert into candidat values(null,'Henri','goley','47 avenue des peupliers','34200','Sete','e@gmail.com',sha1('vfdffrg9'),'candidat',7,"Actuellement en recherche d\'emploi dans le mondre de la culture");

//===============================================PROFESSIONELS========================================================

insert into professionels values(null,'BOLISANT','58 rue des merises','93300','Aubervilliers','f@gmail.com',sha1('septahuit'),'342533213','professionel');
insert into professionels values(null,'ANNA','60 rue deboulots','65150','Saint-laurent','g@gmail.com',sha1('oubzouz'),'34fefefefgb','professionel');
insert into professionels values(null,'NONEKI','12 boulevard des batignolles','75012','Paris','h@gmail.com',sha1('123456789'),'0394875783','professionel');
insert into professionels values(null,'TESSUR','6 avenue des pins','13000','Marseille','i@gmail.com',sha1('dzdozddd'),'23456543','professionel');
insert into professionels values(null,'VELUX','78 rue des sapins','65230','Castelanu magnoac','j@gmail.com',sha1('fezfzfz'),'38746289','professionel');

//===============================================POSTE===================================================================

insert into poste values(null,'developpeur informatique','CDI',11);
insert into poste values(null,'assistant banquier','CDD',3);
insert into poste values(null,'fleuriste','CDI',6);
insert into poste values(null,'Manager aeronautique','CDI',19);
insert into poste values(null,'Electricien','CDI',8);

//===============================================CANDIDATURE==================================================================

insert into candidature values(null,11,2,94,1,'attente');
insert into candidature values(null,3,3,65,2,'attente');
insert into candidature values(null,4,4,53,3,'valide');
insert into candidature values(null,7,5,78,3,'annuler');
insert into candidature values(null,2,6,23,4,'attente');

//===============================================PROFIL==================================================================

insert into profil values(1);
insert into profil values(2);
insert into profil values(3);
insert into profil values(4);
insert into profil values(5);
