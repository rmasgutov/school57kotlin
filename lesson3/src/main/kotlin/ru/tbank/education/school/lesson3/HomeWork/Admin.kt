package ru.tbank.education.school.lesson3.HomeWork

class Admin(
    id: Int,
    username: String,
    email: String,
) : User(id, email, username) {
    override fun Profile(): String {
        return "$username - Admin"
    }

    fun deletePost(post: Post) {
        println("Admin $username deleted post: $post by ${post.author.username}")
    }
}
