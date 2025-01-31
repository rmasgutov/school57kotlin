create table users
(
    id bigint primary key,
    last_name  varchar(30),
    first_name varchar(30),
    gender varchar(6),
    age int,
    email varchar(255) unique,
    password varchar(24),
    token varchar(255),
    photo varchar(255),
    hobby text

);

create table reactions
(
    id bigint primary key,
    reactions_type_id bigint references reactions_type(id),
    sender_id bigint,
    receiver_id bigint
);

create table reactions_type
(
    id bigint primary key,
    type varchar(255),
    emotion_type varchar(255),
    emoji varchar(7),
    description text
)
