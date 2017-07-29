create database shopping default character set utf8;

create table User (
uuid varchar(255) not null ,
email varchar(255) not null,
name varchar(255) not null,
password varchar(255) default null,
primary key(uuid)
);


create table Orders(
uuid varchar(255) not null,
ordercode varchar(255) not null,
address varchar(255),
post varchar(255),
receiver varchar(255),
mobile varchar(255),
usermessage varchar(255),
createDate datetime ,
paydate datetime ,
deliveryDate datetime ,
confirmDate datetime ,
status varchar(255) ,
u_uuid varchar(255) not null,
primary key(uuid),
constraint fk_order_user foreign key(u_uuid) references user(uuid)
);

create table category(
id int(11) auto_increment,
uuid varchar(255) not null unique,
name varchar(255) not null,
description varchar(255),
primary key(uuid)
);

create table subdivide(
id int(11) not null auto_increment,
uuid varchar(255) not null unique,
name varchar(255) not null,
description varchar(255) ,
ct_uuid varchar(255) not null,
primary key(id),
constraint fk_subdivide_category foreign key(ct_uuid) references category(uuid)
);
create table product(
id int(11) auto_increment,
uuid varchar(255) not null unique,
name varchar(255) not null,
orignalprice float ,
promoteprice float,
stock int(11),
createdate datetime,
sb_uuid varchar(255) not null,
primary key(id),
constraint fk_propduct_subdivide foreign key(sb_uuid) references subdivide(uuid)
);


create  table OrderItem (
id int(11) not null auto_increment,
pd_uuid varchar(255) not null,
o_uuid varchar(255) not null,
u_uuid varchar(255) not null,
number int(11),
primary key(id),
constraint fk_oderitem_product foreign key(pd_uuid) references product(uuid),
constraint fk_oderitem_oder foreign key(o_uuid) references orders(uuid),
constraint fk_oderitem_user foreign key(u_uuid) references user(uuid)
);

create table property(
id int(11) auto_increment,
uuid varchar(255) not null unique,
name varchar(255) not null,
sb_uuid varchar(255) not null,
primary key(id),
constraint fk_property_subdivide foreign key(sb_uuid) references subdivide(uuid)
);

create table propertyvalue(
id int(11) auto_increment,
value varchar(255) default null,
pp_uuid varchar(255) not null,
pd_uuid varchar(255) not null,
primary key(id),
constraint fk_propertyvalue_propery foreign key(pp_uuid) references property(uuid),
constraint fk_propertyvalue_product foreign key(pd_uuid) references product(uuid)
);

CREATE TABLE productimage (
  id int(11) NOT NULL AUTO_INCREMENT,
  pd_uuid varchar(255) DEFAULT NULL,
  type varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_productimage_product FOREIGN KEY (pd_uuid) REFERENCES product (uuid)
) ;

CREATE TABLE review (
  id int(11) NOT NULL AUTO_INCREMENT,
  content varchar(4000) DEFAULT NULL,
  u_uuid varchar(255) DEFAULT NULL,
  pd_uuid varchar(255) DEFAULT NULL,
  createDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_review_product FOREIGN KEY (pd_uuid) REFERENCES product (uuid),
  CONSTRAINT fk_review_user FOREIGN KEY (u_uuid) REFERENCES user (uuid)
);
