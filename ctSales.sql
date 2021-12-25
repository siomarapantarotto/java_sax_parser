/*
 * This scrip creates sequence SalesIDSeq and table Sales to store
 * data extracted from file Sales.xml via a java SAX Parser.
 * Author	Siomara Pantarotto
 * Date		April, 04, 2001
 */

drop	sequence SalesIDSeq;

create	sequence SalesIDSeq increment by 1 start with 1;

drop	table Sales cascade constraints;

create	table Sales
(
	saleID		number(10),
	saleDate	date,
	fullName	varchar2(50),
	street		varchar2(50),
	city		varchar2(30),
	state		varchar2(2),
	zipCode		varchar2(10),
	country		varchar2(30),
	product		varchar2(50),
	quantity	number(10),
	price		number(8,2),
	primary key(saleID)
);
