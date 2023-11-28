-- 기본 정렬은 id
create table temp(
    id int not null auto_increment,
    subject varchar(255),
    grp int, -- 게시물 그룹화
    seq int, -- 그룹화한 자료를 2차 정렬
    depth int, -- 들여쓰기
    primary key(id)
);

use youngdb;

create table temp(
    id int not null auto_increment,
    subject varchar(255),
    grp int,
    seq int,
    depth int,
    primary key(id)
);

insert into temp values(null, '1번째 게시물', 0 ,0, 0);
insert into temp values(null, '2번째 게시물', 0 ,0, 0);
insert into temp values(null, '3번째 게시물', 0 ,0, 0);

select * from temp order by id desc;

insert into temp values(null, '[답글]2번째 게시물', 0, 0, 0);
select * from temp order by id desc;


insert into temp values(null, '1번째 게시물', 1, 1, 1);
insert into temp values(null, '2번째 게시물', 2, 1, 1);
insert into temp values(null, '3번째 게시물', 3, 1, 1);

-- 철수
insert into temp values(null, '[철수의-2번답글]2번째 게시물', 2, 1, 2);

-- 영희
insert into temp values(null, '[영희의- 2번답글]2번째 게시물', 2, 2, 2);


insert into temp values(null, '[철수의 답글에 대한 답글]2번째 게시물', 2, 1, 3);


select * from temp order by grp desc, seq asc;

insert into temp values(null, '[홍길동의-1번답글] 1번째 게시물', 1, 1, 2);
insert into temp values(null, '[코리아의-1번답글] 1번째 게시물', 1, 2, 2);


create table board(
id int not null auto_increment,
subject varchar(255) not null,
writer varchar(10) not null,
content text,
visit int,
regdate date,
orgName varchar(255),
savedFileName varchar(255),
savedFilePathName varchar(255),
savedFileSize bigint,
folderName varchar(10),
ext varchar(20),
grp int,
seq int,
depth int,
primary key(id)
);