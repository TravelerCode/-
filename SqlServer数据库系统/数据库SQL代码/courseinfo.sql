create table courseinfo(
	cno int not null primary key,
	cname varchar(20) not null unique,
	before_cour tinyint not null default 0
)
