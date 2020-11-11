CREATE TABLE users (
	username varchar(20) NOT NULL,
	password varchar(20) DEFAULT NULL,
	isadmin tinyint check([isadmin]=1 OR [isadmin]=0) DEFAULT NULL,  --1代表'是'，0代表'不是'。
	PRIMARY KEY (username)
)
