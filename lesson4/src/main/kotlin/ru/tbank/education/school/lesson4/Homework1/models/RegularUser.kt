package ru.tbank.education.school.lesson4.Homewor1.models

import interfaces.SocialActions
import interfaces.ContentManagement

class RegularUser(
    id: Int,
    email: String,
    username: String
) : User(id, email, username), SocialActions, ContentManagement {

    private val posts = mutableListOf<Post>()
    private val friends = mutableListOf<RegularUser>()

    val numOfFriends: Int
        get() = friends.size

    override fun getProfile(): String {
        return "$username\nID: $id \nPosts: ${posts.size} \nFriends: ${friends.size}"
    }

    override fun addFriend(friend: RegularUser) {
        if (friend !in friends && friend != this) {
            friends.add(friend)
            if (this !in friend.getFriends()) {
                friend.addFriend(this)
            }
        }
    }

    override fun getFriends(): List<RegularUser> {
        return friends.toList()
    }

    override fun createPost(content: String): Post {
        require(content.isNotBlank()) { "Post content cannot be empty" }
        require(content.length <= 500) { "Post content too long" }

        val newPost = Post(posts.size + 1, this, content)
        posts.add(newPost)
        return newPost
    }

    override fun getPosts(): List<Post> {
        return posts.toList()
    }
}