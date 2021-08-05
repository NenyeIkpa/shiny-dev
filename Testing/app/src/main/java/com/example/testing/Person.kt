package com.example.testing

import java.io.Serializable

data class Person(
    val name: String,
    val phoneNum: String,
    val email: String,
    val sex: String,
    val password: String,
    val confirmPassword: String
) : Serializable