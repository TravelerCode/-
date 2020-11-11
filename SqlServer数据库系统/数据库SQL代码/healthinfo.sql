create table healthinfo(
	id int not null primary key,
	sno int not null,
	sname varchar(20) not null,
	weight float default null,
	defferentiate varchar(4) check([defferentiate] = '����' or [defferentiate] = 'ɫ��' or [defferentiate] = 'ɫä') default null,
	left_sight float default null,
	right_sight float default null,
	left_ear varchar(4) check([left_ear] = '����' or [left_ear] = 'ƫ��') default null,
	legs varchar(4) check([legs] = '����' or [legs] = '�����') default null,
	pressure varchar(4) check([pressure] = '����' or [pressure] = 'ƫ��' or [pressure] = 'ƫ��') default null,
	history varchar(50) default null,
	remark text default null,
	constraint healthinfo_FK foreign key (sno) references studentinfo(sno)
)
