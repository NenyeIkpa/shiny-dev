package com.example.testing


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class RegistrationInputTest {

//    bring in my subject under test (SUT) to the test environment
    private lateinit var validate:RegistrationInputValidation

    //initialize the SUT
    @Before
    fun setUp() {
        validate = RegistrationInputValidation
    }

    @Test
    fun ` non empty and valid entries return true`() {
        val output = validate.validateAnyInputIsEmpty(
            "Li Wong",
            "08063492057",
            "li.wong@decagon.dev",
            "liwong@123",
            "liwong@123",

            )
        assertThat(output).isTrue()
    }

    @Test
    fun `empty field returns false`() {
        val output = validate.validateAnyInputIsEmpty(
            "Li Wong",
            "08063492057",
            "",
            "liwong@123",
            "liwong@123"
        )
            assertThat(output).isFalse()
    }

    @Test
    fun `phone number is valid`() {
        val output = validate.validatePhoneNumber(
            "08038814264"
        )
        assertThat(output).isTrue()
    }

    @Test
    fun `phone number is not valid`() {
        val output = validate.validatePhoneNumber(
            "08232345678"
        )
        assertThat(output).isFalse()
    }

    @Test
    fun `email address is valid`() {
        val output = validate.validateEmail(
            "android2@gmail.com"
        )
        assertThat(output).isTrue()
    }

    @Test
    fun `email address is not valid`() {
        val output = validate.validateEmail(
            "node@.net.com"
        )
        assertThat(output).isFalse()
    }


    @Test
    fun `is a field in sex selection`() {
        val output = validate.validateSexSelection(
            "Male"
        )
        assertThat(output).isTrue()
    }

    @Test
    fun `is not a field in sex selection`() {
        val output = validate.validateSexSelection(
            "male"
        )
        assertThat(output).isFalse()
    }

    @Test
    fun `password entered is valid`(){
        val output = validate.validatePassword(
            "abc@123"
        )
        assertThat(output).isTrue()
    }


    @Test
    fun `validate password entered`(){
        val output = validate.validatePassword(
            "abc123"
        )
        assertThat(output).isFalse()
    }


    @Test
    fun `confirm password is same as password`(){
        val output = validate.validateConfirmPassword(
            "12abCD**#",
        "12abCD**#"
        )
        assertThat(output).isTrue()
    }

    @Test
    fun `confirm password is not same as password`(){
        val output = validate.validateConfirmPassword(
            "12abC**#",
            "12abCD**#"
        )
        assertThat(output).isFalse()
    }
}