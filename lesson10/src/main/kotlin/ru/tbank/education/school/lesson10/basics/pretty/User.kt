package ru.tbank.education.school.lesson10.basics.pretty

@MyPrettyClass
class User(
    @MyPrettyParameter private val id: String
)

class Wrapper<@MyPrettyType T>