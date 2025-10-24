package ru.tbank.education.school.lesson7.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson7.practise.task3.User
import ru.tbank.education.school.lesson7.practise.task3.hasRole

class HasRoleTest {

    @Test
    fun `returns true when user has role`() {
        val user = User("Иван", 30, "Москва", setOf("USER", "ADMIN"))

        assertTrue(user hasRole "ADMIN")
        assertTrue(user hasRole "USER")
    }

    @Test
    fun `returns false when user does not have role`() {
        val user = User("Ольга", 25, "Казань", setOf("USER"))

        assertFalse(user hasRole "ADMIN")
        assertFalse(user hasRole "MANAGER")
    }

    @Test
    fun `returns false when user has no roles`() {
        val user = User("Петр", 40, "Томск")

        assertFalse(user hasRole "USER")
        assertFalse(user hasRole "ANYTHING")
    }

    @Test
    fun `role check is case sensitive`() {
        val user = User("Анна", 28, "Москва", setOf("Admin"))

        assertFalse(user hasRole "ADMIN")
        assertTrue(user hasRole "Admin")
    }

    @Test
    fun `works with multiple roles`() {
        val user = User("Максим", 35, "Екатеринбург", setOf("USER", "MANAGER", "ADMIN"))
        val result = listOf("USER", "ADMIN", "MANAGER").all { user hasRole it }

        assertTrue(result)
    }
}
