package ru.tbank.education.school.lesson8.practise

data class User(
    val id: Int,
    val username: String,
    var email: String,
    var isActive: Boolean = true
)

class UserService {
    private val users = mutableMapOf<Int, User>()
    private var nextId = 1
    
    fun registerUser(username: String, email: String): User {
        require(email.contains("@")) { "Некорректный email" }
        require(users.values.none { it.email == email }) { "Пользователь с таким email уже существует" }
        
        val user = User(nextId, username, email)
        users[nextId] = user
        nextId++
        return user
    }
    
    fun findUserById(id: Int): User? = users[id]
    
    fun findUserByUsername(username: String): User? = 
        users.values.find { it.username == username }
    
    fun deactivateUser(id: Int): Boolean {
        val user = users[id]
        return if (user != null && user.isActive) {
            user.isActive = false
            true
        } else {
            false
        }
    }
    
    fun activateUser(id: Int): Boolean {
        val user = users[id]
        return if (user != null && !user.isActive) {
            user.isActive = true
            true
        } else {
            false
        }
    }
    
    fun getActiveUsers(): List<User> = users.values.filter { it.isActive }
    
    fun getAllUsers(): List<User> = users.values.toList()
    
    fun deleteUser(id: Int): Boolean = users.remove(id) != null
    
    fun updateEmail(id: Int, newEmail: String): Boolean {
        require(newEmail.contains("@")) { "Некорректный email" }

        val user = users[id]
        return if (user != null) {
            user.email = newEmail
            true
        } else {
            false
        }
    }
}