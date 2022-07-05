create table if not exists users
(
    id         bigint unsigned not null auto_increment primary key,
    email      varchar(100)    not null,
    first_name varchar(50),
    last_name  varchar(50),
    age        int unsigned,
    gender     varchar(13),
    city       varchar(50),
    interests  varchar(200),
    password   varchar(100)    not null
);

create index first_name_last_name_idx on users (first_name, last_name);

create table if not exists users_friends
(
    user_id   bigint unsigned not null,
    friend_id bigint unsigned not null,

    primary key (user_id, friend_id),
    foreign key (user_id) references users (id),
    foreign key (friend_id) references users (id)
);