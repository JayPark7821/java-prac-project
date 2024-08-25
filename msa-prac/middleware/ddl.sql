create database postdb;
use postdb;

create user 'post-server'@'%' identified by 'password!';
grant all privileges on postdb.* to 'post-server'@'%';

create table post
(
    post_id         int auto_increment
        primary key,
    image_id        varchar(255)                       not null,
    uploader_id     int                                not null,
    upload_datetime datetime default CURRENT_TIMESTAMP null,
    contents        text                               null,
    status          varchar(15)                        null
);

create database userdb;
use userdb;

create user 'user-server'@'%' identified by 'password!';
grant all privileges on userdb.* to 'user-server'@'%';

create table user
(
    user_id  int auto_increment
        primary key,
    username varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    last_post_datetime datetime,
    last_post_id varchar(255)
);

create table follow
(
    follow_id       int auto_increment
        primary key,
    user_id         int                                not null,
    follower_id     int                                not null,
    follow_datetime datetime default CURRENT_TIMESTAMP null
);
