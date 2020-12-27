create database NotificationProject;
use NotificationProject;
create table notification_templetes
(
	templete_id int(20) NOT NULL,
    subject varchar(100),
    content varchar(800),
    language varchar(100),
    num_placholders int(20),
    reciever varchar(100),
    primary key(templete_id)
);
use NotificationProject;
create table smss
(
	templete_id int(20) NOT NULL,
    sms_id int(20) Not NULL,
    subject varchar(80),
	content varchar(800),
    phone varchar(50),
    primary key(sms_id),
    foreign key(templete_id) REFERENCES notification_templetes(templete_id)
);
create table emails
(
	subject varchar(80),
    content varchar(800),
    email_id int(20) Not NULL,
    email varchar(100),
   templete_id int(20),
    primary key(email_id),
    FOREIGN KEY (templete_id) REFERENCES notification_templetes(templete_id)
);