package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Test
import org.junit.Assert.*

class ProfileModelTest {

    @Test
    fun `ProfileModel is created correctly with valid data`() {
        val username = Username("testuser")
        val email = Email("test@example.com")
        val profile = ProfileModel(
            userId = "123",
            username = username,
            email = email
        )

        assertEquals("testuser", profile.username.value)
        assertEquals("test@example.com", profile.email.value)
    }

    @Test
    fun `Username value object throws exception for blank value`() {
        assertThrows(IllegalArgumentException::class.java) {
            Username("   ")
        }
    }

    @Test
    fun `Email value object throws exception for invalid format`() {
        assertThrows(IllegalArgumentException::class.java) {
            Email("invalid-email")
        }
    }

    @Test
    fun `Email value object is created correctly with valid format`() {
        val validEmail = "valid@example.com"
        val email = Email(validEmail)
        assertEquals(validEmail, email.value)
    }
}