package ru.tbank.education.school.lesson8.practise

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows

@Disabled
class UserServiceTest {
    
    private lateinit var userService: UserService
    
    @BeforeEach
    fun setUp() {
        userService = UserService()
    }
    
    @Test
    @DisplayName("Успешная регистрация пользователя")
    fun `successful user registration`() {
        val user = userService.registerUser("john_doe", "john@example.com")
        
        assertEquals(1, user.id)
        assertEquals("john_doe", user.username)
        assertEquals("john@example.com", user.email)
        assertTrue(user.isActive)
    }
    
    @Test
    @DisplayName("Регистрация пользователя с пустым именем вызывает исключение")
    fun `registration with empty username throws exception`() {
        val exception = assertThrows<IllegalArgumentException> {
            userService.registerUser("", "test@example.com")
        }
        assertEquals("Имя пользователя не может быть пустым", exception.message)
    }
    
    @Test
    @DisplayName("Регистрация пользователя с некорректным email вызывает исключение")
    fun `registration with invalid email throws exception`() {
        val exception = assertThrows<IllegalArgumentException> {
            userService.registerUser("test", "invalid-email")
        }
        assertEquals("Некорректный email", exception.message)
    }
    
    @Test
    @DisplayName("Регистрация пользователя с существующим именем вызывает исключение")
    fun `registration with existing username throws exception`() {
        userService.registerUser("john", "john@example.com")
        
        val exception = assertThrows<IllegalArgumentException> {
            userService.registerUser("john", "another@example.com")
        }
        assertEquals("Пользователь с таким именем уже существует", exception.message)
    }
    
    @Nested
    @DisplayName("Поиск пользователей")
    inner class FindUsersTests {
        
        @BeforeEach
        fun setUp() {
            userService.registerUser("user1", "user1@example.com")
            userService.registerUser("user2", "user2@example.com")
        }
        
        @Test
        @DisplayName("Поиск пользователя по ID")
        fun `find user by id`() {
            val user = userService.findUserById(1)
            
            assertNotNull(user)
            assertEquals("user1", user?.username)
        }
        
        @Test
        @DisplayName("Поиск пользователя по имени")
        fun `find user by username`() {
            val user = userService.findUserByUsername("user2")
            
            assertNotNull(user)
            assertEquals("user2@example.com", user?.email)
        }
        
        @Test
        @DisplayName("Поиск несуществующего пользователя возвращает null")
        fun `find non-existent user returns null`() {
            assertNull(userService.findUserById(999))
            assertNull(userService.findUserByUsername("unknown"))
        }
    }
    
    @Nested
    @DisplayName("Активация и деактивация пользователей")
    inner class ActivationTests {
        
        @BeforeEach
        fun setUp() {
            userService.registerUser("active_user", "active@example.com")
        }
        
        @Test
        @DisplayName("Успешная деактивация пользователя")
        fun `successful user deactivation`() {
            val result = userService.deactivateUser(1)
            
            assertTrue(result)
            assertFalse(userService.findUserById(1)!!.isActive)
        }
        
        @Test
        @DisplayName("Успешная активация пользователя")
        fun `successful user activation`() {
            userService.deactivateUser(1)
            val result = userService.activateUser(1)
            
            assertTrue(result)
            assertTrue(userService.findUserById(1)!!.isActive)
        }
        
        @Test
        @DisplayName("Получение списка активных пользователей")
        fun `get active users list`() {
            userService.registerUser("user2", "user2@example.com")
            userService.deactivateUser(1)
            
            val activeUsers = userService.getActiveUsers()
            
            assertEquals(1, activeUsers.size)
            assertEquals("user2", activeUsers[0].username)
        }
    }
    
    @Nested
    @DisplayName("Управление пользователями")
    inner class ManagementTests {
        
        @BeforeEach
        fun setUp() {
            userService.registerUser("test_user", "test@example.com")
        }
        
        @Test
        @DisplayName("Успешное удаление пользователя")
        fun `successful user deletion`() {
            val result = userService.deleteUser(1)
            
            assertTrue(result)
            assertNull(userService.findUserById(1))
        }
        
        @Test
        @DisplayName("Удаление несуществующего пользователя возвращает false")
        fun `delete non-existent user returns false`() {
            assertFalse(userService.deleteUser(999))
        }
        
        @Test
        @DisplayName("Успешное обновление email")
        fun `successful email update`() {
            val result = userService.updateEmail(1, "new@example.com")
            
            assertTrue(result)
            assertEquals("new@example.com", userService.findUserById(1)!!.email)
        }
        
        @Test
        @DisplayName("Обновление email на существующий вызывает исключение")
        fun `update to existing email throws exception`() {
            userService.registerUser("another", "another@example.com")
            
            val exception = assertThrows<IllegalArgumentException> {
                userService.updateEmail(1, "another@example.com")
            }
            assertEquals("Email уже используется", exception.message)
        }
    }
    
    @Test
    @DisplayName("Автоинкремент ID при регистрации")
    fun `auto increment id on registration`() {
        val user1 = userService.registerUser("user1", "user1@example.com")
        val user2 = userService.registerUser("user2", "user2@example.com")
        val user3 = userService.registerUser("user3", "user3@example.com")
        
        assertEquals(1, user1.id)
        assertEquals(2, user2.id)
        assertEquals(3, user3.id)
    }
    
    @Test
    @DisplayName("Получение всех пользователей")
    fun `get all users`() {
        userService.registerUser("user1", "user1@example.com")
        userService.registerUser("user2", "user2@example.com")
        
        val allUsers = userService.getAllUsers()
        
        assertEquals(2, allUsers.size)
        assertTrue(allUsers.any { it.username == "user1" })
        assertTrue(allUsers.any { it.username == "user2" })
    }
}