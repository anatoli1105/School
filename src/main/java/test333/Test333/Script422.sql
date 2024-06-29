
CREATE TABLE car2 (
    Id SERIAL,
    Brand varchar,
    Color varchar,
    Number INTEGER primary key
);
INSERT INTO car2 (Id,Brand,Color,Number) VALUES (1,'bmw','red',2426666)
INSERT INTO car2 (Id,Brand,Color,Number) VALUES (2,'vw','black',6666)
INSERT INTO car2 (Id,Brand,Color,Number) VALUES (3,'oka','grey',7777)
INSERT INTO car2 (Id,Brand,Color,Number) VALUES (4,'volvo','silver',99999)
select * from car2
CREATE TABLE person3 (
    Id SERIAL,
    Names varchar primary key ,
    Age INTEGER,
    CarLicense integer,
    NumberCar_Number INTEGER references car2 (number)
);
INSERT INTO person3 (Id,Names,Age,CarLicense,NumberCar_Number) VALUES (1,'ivan',18,242,99999)
INSERT INTO person3 (Id,Names,Age,CarLicense,NumberCar_Number) VALUES (2,'garry',32,24772,6666)
INSERT INTO person3 (Id,Names,Age,CarLicense,NumberCar_Number) VALUES (3,'pety',48,66242,7777)
INSERT INTO person3 (Id,Names,Age,CarLicense,NumberCar_Number) VALUES (4,'oly',28,267842,99999)
select * from person3
SELECT person3.Names, car2.number,car2.brand
FROM person3
INNER JOIN car2 ON person3. NumberCar_Number = car2.number
