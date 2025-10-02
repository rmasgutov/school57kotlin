package ru.tbank.education.school.lesson4.Homewor1.models

data class Post(
    val id: Int,
    val author: RegularUser,
    val content: String,
    var likes: Int = 0
) {
    fun like() {
        likes++
    }

    fun isPopular(): Boolean = likes >= 10
}