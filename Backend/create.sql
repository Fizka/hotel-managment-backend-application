create table comment (id_comment bigint not null, mark integer, text_value varchar(255), customer_id bigint not null, room_id bigint not null, primary key (id_comment)) engine=InnoDB
create table customer (id_customer bigint not null, email varchar(255), firstname varchar(255), lastname varchar(255), login varchar(255), password varchar(255), privileges int default 0, primary key (id_customer)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table reservation (id_reservation bigint not null, customer varchar(255), end_data date, how_long integer, room varchar(255), start_data date, status bit, customer_id bigint not null, room_id bigint not null, primary key (id_reservation)) engine=InnoDB
create table reservet (id_reservet bigint not null, date_reservation date, customer_id bigint not null, room_id bigint not null, primary key (id_reservet)) engine=InnoDB
create table responsibility (id_responsibility bigint not null, description varchar(255), idworker_responsibility bigint, name_responsibility varchar(255), responsible varchar(255), status varchar(255), primary key (id_responsibility)) engine=InnoDB
create table room (id_room bigint not null, description text, floor integer, max_capacity integer, numberrm integer, price integer, title text, url_images varchar(255), primary key (id_room)) engine=InnoDB
create table worker (id_worker bigint not null, activity varchar(255), dateofbirth datetime, dateofemployment datetime, email varchar(255), firstname varchar(255), lastname varchar(255), login varchar(255) not null, password varchar(255) not null, pesel varchar(255), position varchar(255), privileges int default 1, responsibilities varchar(255), salary integer, workinghours varchar(255), primary key (id_worker)) engine=InnoDB
alter table comment add constraint FKlwqielki359fs4py1a4iw2fdt foreign key (customer_id) references customer (id_customer)
alter table comment add constraint FKihp4hqamxll71q5ut5x66m2ie foreign key (room_id) references room (id_room)
alter table reservation add constraint FK41v6ueo0hiran65w8y1cta2c2 foreign key (customer_id) references customer (id_customer)
alter table reservation add constraint FKm8xumi0g23038cw32oiva2ymw foreign key (room_id) references room (id_room)
alter table reservet add constraint FKmk2o6nvofc9xwxey7osdgwwyi foreign key (customer_id) references customer (id_customer)
alter table reservet add constraint FKh5ljav76hl29lks15lcv2njm2 foreign key (room_id) references room (id_room)
create table comment (id_comment bigint not null, mark integer, text_value varchar(255), customer_id bigint not null, room_id bigint not null, primary key (id_comment)) engine=InnoDB
create table customer (id_customer bigint not null, email varchar(255), firstname varchar(255), lastname varchar(255), login varchar(255), password varchar(255), privileges int default 0, primary key (id_customer)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table reservation (id_reservation bigint not null, customer varchar(255), end_data date, how_long integer, room varchar(255), start_data date, status bit, customer_id bigint not null, room_id bigint not null, primary key (id_reservation)) engine=InnoDB
create table reservet (id_reservet bigint not null, date_reservation date, customer_id bigint not null, room_id bigint not null, primary key (id_reservet)) engine=InnoDB
create table responsibility (id_responsibility bigint not null, description varchar(255), idworker_responsibility bigint, name_responsibility varchar(255), responsible varchar(255), status varchar(255), primary key (id_responsibility)) engine=InnoDB
create table room (id_room bigint not null, description text, floor integer, max_capacity integer, numberrm integer, price integer, title text, url_images varchar(255), primary key (id_room)) engine=InnoDB
create table worker (id_worker bigint not null, activity varchar(255), dateofbirth datetime, dateofemployment datetime, email varchar(255), firstname varchar(255), lastname varchar(255), login varchar(255) not null, password varchar(255) not null, pesel varchar(255), position varchar(255), privileges int default 1, responsibilities varchar(255), salary integer, workinghours varchar(255), primary key (id_worker)) engine=InnoDB
alter table comment add constraint FKlwqielki359fs4py1a4iw2fdt foreign key (customer_id) references customer (id_customer)
alter table comment add constraint FKihp4hqamxll71q5ut5x66m2ie foreign key (room_id) references room (id_room)
alter table reservation add constraint FK41v6ueo0hiran65w8y1cta2c2 foreign key (customer_id) references customer (id_customer)
alter table reservation add constraint FKm8xumi0g23038cw32oiva2ymw foreign key (room_id) references room (id_room)
alter table reservet add constraint FKmk2o6nvofc9xwxey7osdgwwyi foreign key (customer_id) references customer (id_customer)
alter table reservet add constraint FKh5ljav76hl29lks15lcv2njm2 foreign key (room_id) references room (id_room)
