create table users
(
    id bigserial
        constraint users_id_pk primary key (id),
    last_name  varchar(255)
        not null,
    first_name varchar(255)
        not null,
    gender varchar(6)
        check (gender in ('male', 'female')), -- "male" или "female"
    age integer
        constraint check_age check (age is null or age > 18),
    photo_url varchar(255),
);

create table reaction_types
(
    id bigserial
        constraint reaction_types_id_pk primary key (id),
    type varchar(255)
        not null,
    emotion_type varchar(255),

    constraint unique_reaction_types_data unique (type, emotion_type)
);

create table reactions
(
    id bigserial
        constraint reactions_id_pk primary key (id),
    reaction_type_id bigint
        not null references reaction_types(id),
    sender_user_id bigint
        not null references users(id),
    receiver_user_id bigint
        not null references users(id),

    constraint unique_reactions_data unique (reaction_type_id, sender_user_id, receiver_user_id)
);