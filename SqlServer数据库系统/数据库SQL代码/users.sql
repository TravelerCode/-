CREATE TABLE users (
	username varchar(20) NOT NULL,
	password varchar(20) DEFAULT NULL,
	isadmin tinyint check([isadmin]=1 OR [isadmin]=0) DEFAULT NULL,  --1����'��'��0����'����'��
	PRIMARY KEY (username)
)
