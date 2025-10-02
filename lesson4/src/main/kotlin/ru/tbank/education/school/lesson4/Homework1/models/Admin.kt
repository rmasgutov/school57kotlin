package ru.tbank.education.school.lesson4.Homewor1.models

import interfaces.Moderator

class Admin(
    id: Int,
    username: String,
    email: String,
) : User(id, email, username), Moderator {

    private val bannedUsers = mutableSetOf<Int>()

    override fun getProfile(): String {
        return "$username - Admin (Banned users: ${bannedUsers.size})"
    }

    override fun deletePost(post: Post) {
        println("Admin $username deleted post: '${post.content}' by ${post.author.username}")
    }

    override fun banUser(user: RegularUser) {
        bannedUsers.add(user.id)
        println("Admin $username banned user: ${user.username}")
    }
}