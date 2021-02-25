
create table air_company (
    id bigint not null auto_increment,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    name varchar(255),
    type varchar(255),
    primary key (id)) engine=InnoDB;

create table airplane (
    id bigint not null auto_increment,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    factory_serial_number_uuid varchar(255),
    flight_distance bigint,
    fuel_capacity bigint,
    name varchar(255),
    number_of_flights integer,
    type varchar(255),
    air_company_id bigint not null,
    primary key (id)) engine=InnoDB;

create table flight (
    id bigint not null auto_increment,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    departure_at datetime(6),
    departure_country varchar(255),
    destination_country varchar(255),
    distance bigint,
    ended_at datetime(6),
    estimated_flight_time time,
    status varchar(255),
    air_company_id bigint not null,
    airplane_id bigint not null,
    primary key (id)) engine=InnoDB;


alter table airplane add constraint FKp9yob98tmyb901cnu35k9uy43
    foreign key (air_company_id) references air_company (id) on delete cascade;
alter table flight add constraint FKebvst1vfvhmhgn73mqs1vt8m7
    foreign key (air_company_id) references air_company (id) on delete cascade;
alter table flight add constraint FKb8t4272gfgo1feyyidvscbjm0
    foreign key (airplane_id) references airplane (id) on delete cascade;