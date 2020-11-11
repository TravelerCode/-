create table licenseinfo(
	id int not null primary key,
	sno int not null unique,
	sname varchar(20) not null unique,
	lno varchar(18) default null unique,
	receive_time date default null,
	receive_name varchar(20) default null,
	remark text default null,
	constraint licenseinfo_FK foreign key (sno) references studentinfo(sno)
)
