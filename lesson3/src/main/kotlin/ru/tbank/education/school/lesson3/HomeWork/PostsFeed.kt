package ru.tbank.education.school.lesson3.HomeWork

class PostsFeed(val user: RegularUser) {
    fun showPosts(): List<Post> {
        val all = mutableListOf<Post>()
        all.addAll(user.getPosts())
        user.getFriends().forEach { friend ->
            all.addAll(friend.getPosts())
        }
        return all
    }
}
