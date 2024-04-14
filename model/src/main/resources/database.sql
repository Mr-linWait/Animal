create table tb_user
(
    id         bigint primary key,
    account    varchar(20) not null,
    userName   varchar(20),
    password   varchar(64) not null,
    tel        varchar(20),
    address    varchar(30),
    email      varchar(20),
    signature  varchar(20),
    userType   varchar(20),
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_message
(
    id           bigint primary key,
    addresserId  varchar(65) not null,
    recipientsId varchar(65) not null,
    messageState varchar(20)  not null,
    content      nvarchar(1000),
    messageType  varchar(20),
    createTime   datetime,
    modifyTime   datetime,
    modifier     varchar(65)
);

create table tb_comment
(
    id         bigint primary key,
    bizId      varchar(65) not null,
    bizType    varchar(20)  not null,
    userId     int not null,
    comment    nvarchar(100),
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_collecanimals
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    userId     varchar(65) not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalSearch
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    applyId    varchar(65) not null,
    applyState varchar(20)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalAdoption
(
    id         bigint primary key,
    animalId   varchar(65) not null,
    applyId    varchar(65) not null,
    applyState varchar(20)  not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
);

create table tb_animalImg
(
    id         bigint primary key,
    animalId   varchar(65)  not null,
    url       varchar(100) not null,
    createTime datetime,
    modifyTime datetime,
    modifier   varchar(65)
)

create table tb_animalHealthInfo
(
    id               bigint primary key,
    animalId         varchar(65) not null,
    sterilization    varchar(20)  not null,
    desinsectization varchar(20)  not null,
    immune           varchar(20)  not null,
    createTime       datetime,
    modifyTime       datetime,
    modifier         varchar(65)
);


create table tb_animal
(
    id          bigint primary key,
    name        varchar(100),
    type        varchar(20),
    species     varchar(20),
    age         int,
    gender      varchar(20),
    animalState varchar(20),
    province    varchar(30),
    city        varchar(30),
    needCardNum int,
    description varchar(200),
    reward int,
        createTime       datetime,
    modifyTime       datetime,
    modifier         varchar(65)
)