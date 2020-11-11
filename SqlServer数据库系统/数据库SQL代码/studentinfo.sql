create table studentinfo(
	sno int not null primary key,
	sname varchar(20) not null,
	sex varchar(2) check([sex] = '��' OR [sex] = 'Ů') not null,
	age tinyint default null,
	identify varchar(18) not null unique,
	tel varchar(15) default null,
	car_type char(1) check([car_type]='A' or [car_type]='B' or [car_type]='C') not null,
	in_time date not null,
	out_time date default null,
	state varchar(4) check([state] = 'ѧϰ' or [state] = '��ҵ' or [state] = '��ѧ') not null,
	remark text default null
)
