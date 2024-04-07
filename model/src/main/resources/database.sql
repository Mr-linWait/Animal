create table tb_user
(
    id         bigint primary key,
    account    varchar(20) not null,
    userName   varchar(20),
    password   varchar(20) not null,
    tel        varchar(20),
    address    varchar(30),
    email      varchar(20),
    signature  varchar(20),
    userType   varchar(2),
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_message
(
    id           bigint primary key,
    addresserId  varchar(65) not null,
    recipientsId varchar(65) not null,
    messageState varchar(3)  not null,
    content      nvarchar(1000),
    messageType  varchar(3),
    createTime   datetime,
    modifyTime   datetime,
    modifier     varchar(65)
);

create table tb_comment
(
    id         bigint primary key,
    bizId      varchar(65) not null,
    bizType    varchar(3)  not null,
    comment    nvarchar(100),
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_collecanimals
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    animalId   varchar(65) not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalSearch
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    applyId    varchar(65) not null,
    applyState varchar(3)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalAdoption
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    applyId    varchar(65) not null,
    applyState varchar(3)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalImg
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    path varchar(100)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
)

create table tb_animalHealthInfo
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    sterilization    varchar(3) not null,
    desinsectization varchar(3)  not null,
    immune varchar(3)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_user
(
    id         bigint primary key,
    name    varchar(20) not null,
    type   varchar(3),
    species   varchar(20),
    age        int,
    gender    varchar(3),
    animalState      varchar(20),
    province  varchar(20),
    city   varchar(2),
    needCardNum int ,
    description varchar(100),
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);