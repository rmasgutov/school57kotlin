# SQL

### Запуск Postgres
#### Установка дистрибутива
https://www.postgresql.org/download/

#### Запуск контейнера
```bash
docker run --name postgres-container -e\
 POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=mydatabase\
  -p 5432:5432 -v pgdata:/var/lib/postgresql/data -d postgres
```

#### Интерфейс для управления 
https://dbeaver.io/download/

### Создание базы данных
```sql 
create table public.users
(
    id         bigserial
        constraint user_pk
            primary key,
    last_name  varchar(255) not null,
    first_name varchar(255)
);
```

```sql
create table public.account
(
    id      bigserial
        constraint account_pk
            primary key,
    user_id bigint           not null,
    amount  bigint default 0 not null,
    constraint check_name
        check (amount > 0)
);
```
