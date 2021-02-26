insert into air_company (
    id,created_at,updated_at,name,type)
values (1, '1980-12-09 00:00:00', '1980-12-09 00:00:00', 'Abelag' , 'BUSINESS_AVIATION' ),
       (2, '1999-12-09 00:00:00', '1999-12-09 00:00:00', 'Avies Air Company' , 'NATIONAL_AIRLINE' ),
       (3, '1970-02-01 00:00:00', '1970-02-01 00:00:00', 'Aigle Azur' , 'CHARTER_FLIGHTS' ),
       (4, '2019-08-09 00:00:00', '2019-08-09 00:00:00', 'Air Antwerp' , 'REGIONAL_AIRLINES' ),
       (5, '1986-11-01 00:00:00', '1986-11-01 00:00:00', 'Air Atlanta Icelandic' , 'CHARTER_FLIGHTS' ),
       (6, '2016-12-09 00:00:00', '2016-12-09 00:00:00', 'Air Belgium' , 'LONG_ROAD_FLIGHTS' ),
       (7, '1990-01-09 00:00:00', '1990-01-09 00:00:00', 'Air Corsica' , 'NATIONAL_AIRLINE' );

INSERT INTO airplane (
    ID, CREATED_AT, UPDATED_AT, FACTORY_SERIAL_NUMBER_UUID, FLIGHT_DISTANCE, FUEL_CAPACITY, NAME, NUMBER_OF_FLIGHTS, TYPE, AIR_COMPANY_ID )
    VALUE (1,'2020-12-09 00:00:00','2020-12-09 00:00:00','fghg-2343-BBNB-4343',1950,5730,'ATR-42',1,'PASSENGER',1),
       (2,'2021-01-02 00:00:00','2021-01-02 00:00:00','sfdd-3456-DFSF-2323',2100,8330,'DG-B',1,'FREIGHT',2),
       (3,'2020-10-11 00:00:00','2020-10-11 00:00:00','dddd-6564-GDSS-5223',1300,3910,'BOT-332',1,'PASSENGER',3),
       (4,'2020-04-01 00:00:00','2020-04-01 00:00:00','gtht-5434-FSFS-1234',1400,6600,'QWERTY-666',1,'FREIGHT',4),
       (5,'2020-05-09 00:00:00','2020-05-09 00:00:00','cdfg-5476-HDSD-5434',4320,4230,'ROTOM-4',1,'PASSENGER',5),
       (6,'2020-12-09 00:00:00','2020-12-09 00:00:00','hyjy-2121-GFDR-5342',11950,50730,'AAR',1,'FREIGHT',6),
       (7,'2020-08-09 00:00:00','2020-08-09 00:00:00','cdcd-2111-VEGR-6545',2000,3730,'TTB',1,'PASSENGER',7);

INSERT INTO flight (
    id, created_at, updated_at, departure_at, departure_country, destination_country, distance, ended_at, estimated_flight_time, status, air_company_id, airplane_id)
    VALUE (1,'2020-02-25 01:20:20', '2020-02-25 01:20:20','2020-02-25 02:20:20','UKRAINE','MONACO',1425.94,'2020-02-25 04:30:20','02:10:00','ACTIVE',1,1),
       (2,'2020-02-25 02:40:20', '2020-02-25 02:40:20','2020-02-25 03:40:50','FINLAND','FRANCE',2487.70,'2020-02-25 06:20:50','03:20:00','DELAYED',2,2),
       (3,'2020-02-25 03:00:00', '2020-02-25 03:00:00','2020-02-25 04:00:00','GABON','GAMBIA',3408.75,'2020-02-25 08:45:00','04:45:00','COMPLETED',3,3),
       (4,'2020-02-25 04:20:20', '2020-02-25 04:20:20','2020-02-25 05:20:20','ENGLAND','BOLIVIA',9791.86,'2020-02-25 12:01:20','12:01:00','ACTIVE',4,4),
       (5,'2020-02-25 06:40:20', '2020-02-25 06:40:20','2020-02-25 07:40:20','BRAZIL','BOTSWANA',8646.83,'2020-02-25 18:20:20','10:40:00','ACTIVE',5,5),
       (6,'2020-02-25 10:50:00', '2020-02-25 10:50:00','2020-02-25 11:50:00','CANADA','CAMBODIA',11200,'2020-02-26 00:30:00','13:40:00','PENDING',6,6),
       (7,'2020-02-25 22:22:20', '2020-02-25 22:22:20','2020-02-25 23:00:00','CAMBODIA','MONACO',9495.66,'2020-02-26 10:40:00','11:40:00','COMPLETED',7,7);




