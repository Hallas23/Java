DROP TABLE Salgslinje;
DROP TABLE Salg;
DROP TABLE Pris;
DROP TABLE Prisliste;
DROP TABLE Produkt;
DROP TABLE Produktgruppe;



CREATE TABLE Produktgruppe (
navn VARCHAR(30),
PRIMARY KEY(navn)
);

CREATE TABLE Produkt (
id varchar(30),
PRIMARY KEY (id),
produktgruppenavn VARCHAR(30),
navn VARCHAR(30),
foreign key (Produktgruppenavn) references Produktgruppe (navn)
);

CREATE UNIQUE INDEX uq_Produktgruppe
	ON Produkt(navn, Produktgruppenavn);

CREATE TABLE Prisliste (
navn VARCHAR(30),
PRIMARY KEY (navn),
);

CREATE TABLE Pris (
ID VARCHAR(30),
PRIMARY KEY (ID),
pris REAL NOT NULL,
rabat REAL NOT NULL,
produktID VARCHAR (30) NOT NULL,
Prislistenavn VARCHAR (30) NOT NULL,
foreign key (produktID) references Produkt (id),
foreign key (Prislistenavn) references Prisliste (navn)
);

CREATE UNIQUE INDEX uq_Pris
	ON Pris(produktID, Prislistenavn);

CREATE TABLE Salg (
ID VARCHAR(30),
PRIMARY KEY (ID),
SalgslinjeID VARCHAR(30) UNIQUE,
Dato date default getdate() 
);

CREATE TABLE Salgslinje (
antal int NOT NULL,
ID VARCHAR(30),
PrisID VARCHAR(30),
aftaltPris REAL,
foreign key (PrisID) references Pris (ID),
foreign key (ID) references Salg (SalgslinjeID)
);


insert into Produktgruppe(navn)
values ('Flasker')

insert into Produktgruppe(navn)
values ('Fadøl')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Klosterbryg', 'Flasker', 'KlosterID')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Forårsbryg', 'Flasker', 'ForårsID')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Klosterbryg', 'Fadøl', 'KlosterID2')

insert into Prisliste(navn)
values ('Fredagsbar')

insert into Prisliste(navn)
values ('Butik')

insert into Pris(ID, pris, produktID, Prislistenavn, rabat)
values ('1', 50, 'KlosterID', 'Fredagsbar',20)

insert into Pris(ID, pris, produktID, Prislistenavn, rabat)
values ('2', 70, 'KlosterID2', 'Butik', 0)

insert into Pris(ID, pris, produktID, Prislistenavn, rabat)
values ('3', 75, 'ForårsID', 'Butik', 0)


/*
Opgave 2 a 
*/


select Produkt.navn AS 'Produktnavn', 
Pris.pris AS 'Pris', 
Pris.rabat AS 'Rabat', 
Prisliste.navn AS 'Prislistenavn'
from Produkt, Pris, Prisliste
where 'Klosterbryg' = Produkt.navn 
AND Produkt.id = Pris.produktID 
AND  pris.Prislistenavn = Prisliste.navn

--TEST

insert into Prisliste(navn)
values ('Arrangement')

insert into Pris (ID, pris, Prislistenavn, produktID, rabat)
values ('4', 50, 'Arrangement', 'KlosterID', 20)

/*
Opgave 2 b
*/

select SUM (pris.rabat * Salgslinje.antal) AS Rabat
from Salgslinje, Salg, Pris
where '1' = Salg.ID 
AND Salg.SalgslinjeID = Salgslinje.ID 
AND Salgslinje.PrisID = pris.ID;

--TEST
insert into Produkt (id, navn, produktgruppenavn)
values ('CelebrationID', 'Celebration', 'Flasker') 

insert into Pris (ID, pris, Prislistenavn, produktID, rabat)
values ('4', 50, 'Fredagsbar', 'CelebrationID', 20)

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '4', 4)


/*Opgave 2 c
*/

select Salg.ID, SUM (ISNULL(Salgslinje.aftaltPris,
	CASE
	WHEN Salgslinje.aftaltPris is not null THEN Salgslinje.aftaltpris
	ELSE (Pris.pris * Salgslinje.antal) - (Pris.rabat * Salgslinje.antal)
	end
	)) AS Beløb
from Salgslinje, Salg, Pris
where '1' = Salg.ID 
AND Salg.SalgslinjeID = Salgslinje.ID 
AND Salgslinje.PrisID = pris.ID
group by Salg.ID;

--TEST

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '1', 2)

insert into Salgslinje(ID, PrisID, antal)
values ('1', '2', 2)

insert into Salgslinje(ID, PrisID, antal, aftaltPris)
values ('1', '1', 2, 100)

/*
Opgave 2 d
*/

SELECT produkt.navn AS Navn, SUM (Salgslinje.antal) AS Antal
from Produkt, Salg, Salgslinje, Pris
WHERE month(Salg.Dato) = month('2019-04-10') 
AND Produkt.ID = pris.produktID 
AND Pris.ID = Salgslinje.PrisID 
AND Salgslinje.ID = Salg.SalgslinjeID
GROUP BY produkt.navn
HAVING SUM (Salgslinje.antal) > 5

--TEST

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '1', 6)

insert into Salgslinje(ID, PrisID, antal)
values ('1', '3', 3)


/*
Opgave 2 e
*/

SELECT Produkt.navn, Produkt.Produktgruppenavn
from Produkt
WHERE  Produkt.navn NOT IN (select Produkt.navn from Produkt
							JOIN Produktgruppe On Produkt.Produktgruppenavn = Produktgruppe.navn
							JOIN Pris On Produkt.id = Pris.produktID
							JOIN Prisliste on Prisliste.navn = Pris.Prislistenavn
							AND Pris.Prislistenavn = 'Fredagsbar'
							);
--TEST

insert into Produkt (id, navn, produktgruppenavn)
values ('CelebrationID', 'Celebration', 'Flasker') 

insert into Pris (ID, pris, Prislistenavn, produktID, rabat)
values ('4', 50, 'Butik', 'CelebrationID', 0)


/*
Opgave 2 f.
*/

select AVG (Belob) AS Average
from (
select Salg.ID, SUM (ISNULL(Salgslinje.aftaltPris,
	CASE
	WHEN Salgslinje.aftaltPris is not null THEN Salgslinje.aftaltpris
	ELSE (Pris.pris * Salgslinje.antal) - (Pris.rabat * Salgslinje.antal)
	end
	)) as Belob
from Salgslinje, Salg, Pris
where Salg.SalgslinjeID = Salgslinje.ID 
AND Salgslinje.PrisID = pris.ID
group by Salg.ID) As Belob

--TEST

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salg(ID, SalgslinjeID)
values ('2', '2')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '1', 2)

insert into Salgslinje(ID, PrisID, antal)
values ('1', '2', 2)

insert into Salgslinje(ID, PrisID, antal, aftaltPris)
values ('1', '1', 2, 100)

insert into Salgslinje(ID, PrisID, antal)
values ('2', '3', 1)

insert into Salgslinje(ID, PrisID, antal)
values ('2', '2', 1)


 /*
Opgave 3
*/

DROP view produktOplysninger;

create view produktOplysninger AS
SELECT  Produkt.navn AS Produkt, Produktgruppe.navn AS Produktgruppe, 
COUNT (Distinct Salg.ID) AS AntalSalg 
FROM Produkt
LEFT JOIN Produktgruppe on Produkt.Produktgruppenavn = Produktgruppe.navn
LEFT JOIN Pris on Produkt.id = Pris.produktID
LEFT JOIN Salgslinje on Salgslinje.PrisID = Pris.ID
LEFT JOIN Salg on Salgslinje.ID = Salg.ID
GROUP BY Produkt.navn, Produktgruppe.navn;

select * from produktOplysninger

--TEST

insert into Produkt (id, navn, produktgruppenavn)
values ('CelebrationID', 'Celebration', 'Flasker') 

insert into Pris (ID, pris, Prislistenavn, produktID, rabat)
values ('4', 50, 'Butik', 'CelebrationID', 0)

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salg(ID, SalgslinjeID)
values ('2', '2')

insert into Salg(ID, SalgslinjeID)
values ('3', '3')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '1', 5)

insert into Salgslinje(ID, PrisID, antal)
values ('2', '3', 5)

insert into Salgslinje(ID, PrisID, antal)
values ('2', '2', 5)

insert into Salgslinje(ID, PrisID, antal)
values ('3', '1', 5)

 /*
 Opgave 4 a
*/

drop procedure UdskrivPrisliste

create procedure UdskrivPrisliste
@Prisliste varchar(30)
as
begin
SELECT Prisliste.navn, Produkt.navn, Pris.pris
from Prisliste, Pris, Produkt
WHERE  @Prisliste = Prisliste.navn 
AND Prisliste.navn = Pris.Prislistenavn 
AND Pris.produktID = Produkt.id
end;

--TEST

exec UdskrivPrisliste 'Butik';

/*
Opgave 4  b
*/

drop procedure setRabatSats

create procedure setRabatSats
@Produktgruppe varchar(30),
@Rabat REAL
as
begin
UPDATE Pris
set pris.rabat = @Rabat
from Produkt, Produktgruppe
where Pris.produktID = Produkt.id
AND Produkt.Produktgruppenavn = @Produktgruppe
end;

--TEST
select * from pris

exec setRabatSats 'Flasker', 30

select * from Pris

/*
Opgave 5
*/

drop trigger sletProduktgruppe;

create trigger sletProduktgruppe
ON Produkt
FOR DELETE 
AS 
	if(select count (Produkt.Produktgruppenavn)
	FROM Produkt, deleted WHERE Produkt.Produktgruppenavn = deleted.Produktgruppenavn) < 1
BEGIN
	DELETE from Produktgruppe WHERE Produktgruppe.navn = (SELECT Produktgruppenavn FROM DELETED)
END;

--TEST

insert into Produktgruppe(navn)
values ('TestGruppe')

insert into Produktgruppe(navn)
values ('TestGruppe2')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Kande', 'testGruppe', 'KandeID')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Spand', 'testGruppe2',  'SpandID')

insert into Produkt(navn, Produktgruppenavn, id)
values ('Skovl', 'testGruppe2', 'SkovlID')

delete Produkt
where Produkt.id = 'KandeID'

delete Produkt
where Produkt.id = 'SpandID'

select * from Produktgruppe


/*
Opgave 6 B
*/

drop procedure	Opgave6B;


select SUM (Belob) AS SamledeSalg
from (
select Produkt.ID, SUM (ISNULL(Salgslinje.aftaltPris,
	CASE
	WHEN Salgslinje.aftaltPris is not null THEN Salgslinje.aftaltpris
	ELSE (Pris.pris * Salgslinje.antal) - (Pris.rabat * Salgslinje.antal)
	end
	)) as Belob
from Salgslinje, Salg, Pris, Produkt
where Salg.SalgslinjeID = Salgslinje.ID 
AND Salgslinje.PrisID = pris.ID 
AND Pris.produktID = Produkt.id 
AND Produkt.navn = 'Klosterbryg'
AND Salg.Dato = '2019-04-11'
group by Produkt.id) as Belob

--TEST

insert into Salg(ID, SalgslinjeID)
values ('1', '1')

insert into Salg(ID, SalgslinjeID)
values ('2', '2')

insert into Salgslinje(ID, PrisID, antal)
values ('1', '1', 2)

insert into Salgslinje(ID, PrisID, antal)
values ('2', '2', 2)


