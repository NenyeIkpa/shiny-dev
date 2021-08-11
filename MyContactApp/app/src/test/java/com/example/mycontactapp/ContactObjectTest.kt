package com.example.mycontactapp

import org.junit.Test

import org.junit.Assert.*

class ContactObjectTest {

    @Test
    fun getFirstLetterOfName_shouldReturnFirstCharacterOfStringPassedInAsConstructor_returnString() {
        val name = "chinenye"
        val result = ContactObject.getFirstLetterOfName(name)
        assertEquals("C", result)
    }
    @Test
    fun getFirstLetterOfName_shouldReturnFirstCharacterOfStringPassedInAsConstructor1_returnString() {
        val name = "Chinenye"
        val result = ContactObject.getFirstLetterOfName(name)
        assertEquals("C", result)
    }
}