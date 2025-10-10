package ru.tbank.education.school.lesson3.HomeWork

data class Post(
    val id: Int,
    val author: RegularUser,
    val content: String,
    var likes: Int = 0
) {
    fun like() {
        likes++
    }
}
