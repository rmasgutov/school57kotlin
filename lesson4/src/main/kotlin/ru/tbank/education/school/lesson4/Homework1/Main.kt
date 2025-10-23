package ru.tbank.education.school.lesson4.Homewor1

import models.RegularUser
import models.Admin
import models.Post
import services.PostsFeed
import interfaces.PostFilter
import interfaces.PostSorter


fun main() {
    val alice = RegularUser(1, "alice@gmail.com", "Alice")
    val bob = RegularUser(2, "bob@gmail.com", "Bob")
    val eve = RegularUser(5, "eve@gmail.com", "Eve")
    val admin = Admin(1000, "adminprotop@tbank.ru", "UltraProAdmin")

    alice.addFriend(bob)
    bob.addFriend(eve)
    eve.addFriend(alice)

    val post1 = alice.createPost("Сегодня прекрасный день для прогулки")
    val post2 = bob.createPost("Только что закончил новый проект на Kotlin")
    val post3 = eve.createPost("Устала делать проект(")

    post1.like()
    post2.like()
    post3.like()
    post1.like()

    println(alice.getProfile())
    println()
    println(bob.getProfile())
    println()
    println(eve.getProfile())
    println()
    println(admin.getProfile())
    println()

    val bobFeed = PostsFeed(bob)
    val bobPosts = bobFeed.showPosts()

    println(bobPosts.size)
    bobPosts.forEachIndexed { i, post ->
        println("${i + 1}. [${post.likes} likes] ${post.author.username}: ${post.content}")
    }

    admin.deletePost(post1)
}
