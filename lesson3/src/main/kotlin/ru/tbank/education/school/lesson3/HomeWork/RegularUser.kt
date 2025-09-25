package ru.tbank.education.school.lesson3.HomeWork

class RegularUser(
    id: Int,
    email: String,
    username: String
) : User(id, email, username) {
    private val posts = mutableListOf<Post>()
    private val friends = mutableListOf<RegularUser>()
    val numOfFriends: Int
        get() = friends.size

    override fun Profile(): String {
        return "$username\nID: $id \nPosts: ${posts.size} \nfriends: ${friends.size}"
    }

    fun addFriend(friend: RegularUser) {
        if (friend !in friends) {
            friends.add(friend)
        }
    }

    fun createPost(textPost: String): Post {
        val newPost = Post(posts.size + 1, this, textPost)
        posts.add(newPost)
        return newPost
    }

    fun getPosts(): List<Post> {
        return posts.toList()
    }

    fun getFriends(): List<RegularUser> {
        return friends.toList()
    }
//    fun deletePost() {}
}
