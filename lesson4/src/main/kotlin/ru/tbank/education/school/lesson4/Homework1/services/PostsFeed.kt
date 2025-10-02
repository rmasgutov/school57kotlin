package ru.tbank.education.school.lesson4.Homewor1.services

import models.RegularUser
import models.Post
import interfaces.PostFilter
import interfaces.PostSorter

class PostsFeed(
    private val user: RegularUser,
    private val filter: PostFilter = PostFilter { true },
    private val sorter: PostSorter = PostSorter { p1, p2 -> p2.likes.compareTo(p1.likes) }
) {

    fun showPosts(): List<Post> {
        val allPosts = mutableListOf<Post>()

        allPosts.addAll(user.getPosts())

        user.getFriends().forEach { friend ->
            allPosts.addAll(friend.getPosts())
        }

        return allPosts
            .filter { filter.filter(it) }
            .sortedWith { p1, p2 -> sorter.compare(p1, p2) }
    }

    fun getPopularPosts(minLikes: Int): List<Post> {
        return showPosts().filter { it.likes >= minLikes }
    }
}