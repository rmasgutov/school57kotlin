package ru.tbank.education.school.lesson8.practise

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmailValidatorTest {

    @Test
    fun `valid emails should return true`() {
        Assertions.assertTrue(EmailValidator.validateEmail("john@example.com"))
        Assertions.assertTrue(EmailValidator.validateEmail("user.name+tag@domain.co"))
    }

    @Test
    fun `invalid emails should return false`() {
        Assertions.assertFalse(EmailValidator.validateEmail("invalid"))
        Assertions.assertFalse(EmailValidator.validateEmail("name@"))
        Assertions.assertFalse(EmailValidator.validateEmail("@domain.com"))
        Assertions.assertFalse(EmailValidator.validateEmail(" "))
    }

    @Test
    fun `blank string should return false`() {
        Assertions.assertFalse(EmailValidator.validateEmail(""))
        Assertions.assertFalse(EmailValidator.validateEmail("   "))
    }
}
