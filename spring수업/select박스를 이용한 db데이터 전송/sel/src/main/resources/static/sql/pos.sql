use youngdb;

create table pos(
    pos_code char(3),
    pos_name varchar(20),
    dept_code char(3),

    primary key(pos_code),
    foreign key(dept_code) references dept(dept_code) on update cascade on delete restrict
);

insert into pos values('101', '인사부장', '100');

insert into pos values('202', '자재과장', '200');

insert into pos values('303', '비서원', '300');

insert into pos values('901', '대표이사', '900');