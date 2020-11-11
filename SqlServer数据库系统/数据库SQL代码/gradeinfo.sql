create table gradeinfo(
	id int not null primary key,
	sno int not null unique,
	cno int not null,
	last_time date default null,
	times smallint default null,
	grade float default null,
	constraint gradeinfo_FK foreign key (cno) references courseinfo(cno)
)
