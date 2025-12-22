package ru.tbank.education.school.lesson8.practise.smartcast

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class UserProfileTests {

    // --- Tests for isUserOlder ---

    @Test
    fun `isUserOlder returns true when age greater than min`() {
        val profile = UserProfile(name = "Alex", age = 25, email = "a@mail.com")
        assertTrue(isUserOlder(profile, 18))
    }

    @Test
    fun `isUserOlder returns false when age equal min`() {
        val profile = UserProfile(name = "Alex", age = 18, email = "a@mail.com")
        assertFalse(isUserOlder(profile, 18))
    }

    @Test
    fun `isUserOlder returns false when age less than min`() {
        val profile = UserProfile(name = "Alex", age = 15, email = "a@mail.com")
        assertFalse(isUserOlder(profile, 18))
    }

    @Test
    fun `isUserOlder returns false when age is null`() {
        val profile = UserProfile(name = "Alex", age = null, email = "a@mail.com")
        assertFalse(isUserOlder(profile, 18))
    }

    @Test
    fun `isUserOlder returns false when profile is null`() {
        val profile: UserProfile? = null
        assertFalse(isUserOlder(profile, 18))
    }

    // --- Tests for isProfileCompleted ---

    @Test
    fun `isProfileCompleted returns true when name and email are present`() {
        val profile = UserProfile(name = "Alex", age = 20, email = "a@mail.com")
        assertTrue(isProfileCompleted(profile))
    }

    @Test
    fun `isProfileCompleted returns false when name is null`() {
        val profile = UserProfile(name = null, age = 20, email = "a@mail.com")
        assertFalse(isProfileCompleted(profile))
    }

    @Test
    fun `isProfileCompleted returns false when email is null`() {
        val profile = UserProfile(name = "Alex", age = 20, email = null)
        assertFalse(isProfileCompleted(profile))
    }

    @Test
    fun `isProfileCompleted returns false when both name and email are null`() {
        val profile = UserProfile(name = null, age = 20, email = null)
        assertFalse(isProfileCompleted(profile))
    }

    @Test
    fun `isProfileCompleted returns false when profile is null`() {
        val profile: UserProfile? = null
        assertFalse(isProfileCompleted(profile))
    }
}
