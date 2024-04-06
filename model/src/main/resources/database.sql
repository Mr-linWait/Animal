create table tb_user
(
    id  bigint primary key,
    account varchar(20) not null,
    userName varchar(20) ,
    password varchar(20) not null ,
    tel varchar(20),
    address varchar(30),
    email varchar(20),
    signature varchar(20),
    userType varchar(2),
    createTime datetime
);

