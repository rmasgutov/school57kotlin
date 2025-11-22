package ru.tbank.education.school.lesson8.practise.smartcast

data class UserProfile(
    val name: String?,
    val age: Int?,
    val email: String?
)

fun getUserProfile(): UserProfile? {
    // Имитация получения данных (может вернуть null)
    return if ((0..2).random() == 0) {
        null
    } else {
        UserProfile(
            name = if ((0..1).random() == 0) "Алексей" else null,
            age = if ((0..1).random() == 0) 25 else null,
            email = if ((0..1).random() == 0) "alex@mail.com" else null
        )
    }
}

/**
 * 1. Напишите функцию, которая проверяет, что пользователь старше определенного возраста.
 * 2. Напишите функцию, которая проверяет, что у пользователя указаны имя и почта.
 * 3.
 */

fun isUserOlder(profile: UserProfile?, minAge: Int): Boolean {
    TODO()
}

fun isProfileCompleted(profile: UserProfile?): Boolean {
    TODO()
}

fun validateUser(minAge: Int): Pair<Boolean, Boolean> {
    val profile = getUserProfile()

    val older = isUserOlder(profile, minAge)
    val completed = isProfileCompleted(profile)

    return older to completed
}
