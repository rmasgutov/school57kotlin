package ru.tbank.education.school.lesson4.Homewor1.interfaces

import models.RegularUser
import models.Post

interface UserProfile {
    fun getProfile(): String
}

interface SocialActions {
    fun addFriend(user: RegularUser)
    fun getFriends(): List<RegularUser>
}

interface ContentManagement {
    fun createPost(content: String): Post
    fun getPosts(): List<Post>
}

interface Moderator {
    fun deletePost(post: Post)
    fun banUser(user: RegularUser)
}

fun interface PostFilter {
    fun filter(post: Post): Boolean
}

fun interface PostSorter {
    fun compare(p1: Post, p2: Post): Int
}