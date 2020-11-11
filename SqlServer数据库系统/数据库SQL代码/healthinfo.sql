create table healthinfo(
	id int not null primary key,
	sno int not null,
	sname varchar(20) not null,
	weight float default null,
	defferentiate varchar(4) check([defferentiate] = '正常' or [defferentiate] = '色弱' or [defferentiate] = '色盲') default null,
	left_sight float default null,
	right_sight float default null,
	left_ear varchar(4) check([left_ear] = '正常' or [left_ear] = '偏弱') default null,
	legs varchar(4) check([legs] = '正常' or [legs] = '不相等') default null,
	pressure varchar(4) check([pressure] = '正常' or [pressure] = '偏高' or [pressure] = '偏低') default null,
	history varchar(50) default null,
	remark text default null,
	constraint healthinfo_FK foreign key (sno) references studentinfo(sno)
)
