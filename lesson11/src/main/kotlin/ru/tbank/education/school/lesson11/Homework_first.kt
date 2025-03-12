package ru.tbank.education.school.lesson11

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.sql.SQLException

fun main() {
    val url = "jdbc:postgresql://localhost:5432/mydatabase"
    val user = "myuser"
    val password = "mypassword"

    var connection: Connection? = null

    try {
        connection = DriverManager.getConnection(url, user, password)
        println("Подключение к базе данных установлено.")

        val createTableSQL_first = """
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
        )
        """.trimIndent()

        val createTableSQL_second = """
            create table reaction_types
        (
            id bigserial
                constraint reaction_types_id_pk primary key (id),
            type varchar(255)
                not null,
            emotion_type varchar(255),
        
            constraint unique_reaction_types_data unique (type, emotion_type)
        )
        """.trimIndent()

        val createTableSQL_third = """
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
        )
        """.trimIndent()


        val statement: Statement = connection.createStatement()
        statement.executeUpdate(createTableSQL_first)
        statement.executeUpdate(createTableSQL_second)
        statement.executeUpdate(createTableSQL_third)
        println("Таблицы успешно созданы.")

        val resultSet = statement.executeQuery("select 1")
        resultSet.next()
        val string = resultSet.getString(0)
        println(string)

    } catch (e: SQLException) {
        println("Ошибка при подключении к базе данных.")
        e.printStackTrace()
    } finally {
        connection?.close()
        println("Подключение закрыто.")
    }
}
