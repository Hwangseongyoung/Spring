use youngdb;

create table member(
    num int not null auto_increment,
    userid varchar(20) not null,
    passwd varchar(20) not null,
    primary key(num)
);