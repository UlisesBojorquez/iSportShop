package com.example.isportshop.testCases

//import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    //all inputs are empty
    @Test
    fun `empty inputs return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "",
            "",
            "",
            ""
        )
        assertThat(result).isFalse()
    }
    //name of the user is empty
    @Test
    fun `empty username return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "345",
            "2435@gmail.com",
            "2345",
        "2435"
        )
        assertThat(result).isFalse()
    }

    //lastname of the user is empty
    @Test
    fun `empty lastname return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "23123",
            "",
            "2435@gmail.com",
            "2345",
            "2435"
        )
        assertThat(result).isFalse()
    }

    //lastname of the user is empty
    @Test
    fun `empty email return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "23123",
            "123123",
            "",
            "2345",
            "2435"
        )
        assertThat(result).isFalse()
    }

    //all info is placed
    @Test
    fun `valid username and correctly repeated password returns true` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "ulises",
            "bojorquez",
            "ulibojorquez@gmail.com",
            "123456",
            "123456"
        )
        assertThat(result).isTrue()
    }

    //user already exist
    @Test
    fun `username already exist return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "ulises",
            "bojorquez",
            "ulibojorquez@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    //empty password
    @Test
    fun `empty password return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "ulises",
            "bojorquez",
            "ulibojorquez@gmail.com",
            "",
            ""
        )
        assertThat(result).isFalse()
    }
    //password was repeated incorrectly
    @Test
    fun `different password confirmation return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "ulises",
            "bojorquez",
            "ulibojorquez@gmail.com",
            "123456",
            "1234567"
        )
        assertThat(result).isFalse()
    }
    //password contains less than 6 digits
    @Test
    fun `password contain less that 6 digits return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "ulises",
            "bojorquez",
            "ulibojorquez@gmail.com",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

}