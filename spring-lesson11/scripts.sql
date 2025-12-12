create table public.users
(
    id         bigserial
        constraint user_pk
            primary key,
    last_name  varchar(255) not null,
    first_name varchar(255)
);

create table public.account
(
    id      bigserial
        constraint account_pk
            primary key,
    user_id bigint           not null,
    amount  bigint default 0 not null,
    constraint check_name
        check (amount >= 0)
);

-- Изменение таблицы
alter table public.users
    add age integer;

alter table public.users
    add constraint check_age
        check (age > 0);

-- Удаление таблицы
drop table if exists public.users;



-- Отображение и запись пользователей
SELECT t.*
FROM public.users t;

INSERT INTO public.users (id, last_name, first_name) VALUES (DEFAULT, 'Иванов'::varchar(255), 'Иван'::varchar(255));
INSERT INTO public.users (id, last_name, first_name) VALUES (DEFAULT, 'Петров'::varchar(255), 'Петр'::varchar(255));
INSERT INTO public.users (id, last_name, first_name) VALUES (DEFAULT, 'Петров2'::varchar(255), 'Петр2'::varchar(255));

UPDATE public.users SET first_name = 'Дмитрий'::varchar(255) WHERE id = 3::bigint;

DELETE FROM public.users WHERE first_name = 'Дмитрий';

-- Отображение и запись счетов
SELECT t.*
FROM public.account t;

INSERT INTO public.account (id, user_id, amount) VALUES (DEFAULT, 1::bigint, 100::bigint);
INSERT INTO public.account (id, user_id, amount) VALUES (DEFAULT, 2::bigint, 200::bigint);

-- Выборка данных
SELECT u.last_name, u.first_name, u.age, a.amount FROM users u, account a;
SELECT * FROM users join account a on users.id = a.user_id;
SELECT u.last_name, u.first_name, u.age, a.amount FROM users u join account a on u.id = a.user_id;


begin transaction;
insert into account(id, user_id, amount) values (default, 5, -100);
commit;
rollback ;

-- перевод денег со счета на счета
begin transaction;
update account set amount = amount - 10 where id = 1;
update account set amount = amount + 10 where id = 2;
commit;

-- добавим связь пользователя со счетом
alter table account
    add constraint account_users_id_fk
        foreign key (user_id) references users;

-- создали счет, но забыли создать пользователя
begin transaction;
insert into account(id, user_id, amount) values (default, 99, 100);
commit;
rollback;
