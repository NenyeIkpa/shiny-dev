package com.example.testing

import android.content.Context
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern


object RegistrationInputValidation {

        // check that none of the registration fields is empty
        fun validateAnyInputIsEmpty(
            fullName: String,
            phoneNum: String,
            email: String,
            password: String,
            confirmPassword: String
        ): Boolean {

            if (fullName.isEmpty() || phoneNum.isEmpty() || email.isEmpty()
                || password.isBlank() || confirmPassword.isBlank()
            ) {
                return false
            }
                return true
        }

    fun validateName(fullName: String): Boolean {
        val name = fullName.split(" ")
        val specialCharacters = "!@#$%^&*()-+"

        if (name.size > 1 && !(fullName.any { it in specialCharacters })) {
            return true
        }
        return false
    }

        fun validatePhoneNumber(phoneNum: String): Boolean {
            /** Check that it's a Nigerian phone number:11 digit phone number.
             * Begins with either 234 or 0 and the other 10 digits are numbers ranging from 0 to 0.
             */

            if (Pattern.matches("(234|0)(7|8|9)(0|1)[0-9]{8}", phoneNum)) {
                return true
            }
            return false
        }


        //    check that the email address is valid
        fun validateEmail(email: String): Boolean {

            /**Android offers the inbuilt patterns which the entered
            data from the EditText field needs to be compared with
            In this case the the entered data needs to compared with
            the EMAIL_ADDRESS, which is implemented same below
             **/

            val emailPattern = Pattern.compile(".+@[a-z]+\\.[a-z]+");
            val emailMatcher = emailPattern.matcher(email);

            if (emailMatcher.matches()) {
                return true
            }
            return false
        }

        fun validateSexSelection(sex: String): Boolean {
            if (sex == "Male" || sex == "Female") {
                return true
            }
            return false
        }


        fun validatePassword(password: String): Boolean {

            /** Define initial number of conditions the password needs to satisfy: Minimum number of characters
             * to make the password strong is 6. Also, password must contain an alphabet and a
             * specialCharacter respectively
             */

            val numbers = "0123456789"
            val alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            val specialCharacters = "!@#$%^&*()-+"

//          check that the password satisfies given conditions
            if (password.length > 5 && password.any { it in numbers } && password.any { it in alphabets }
                && password.any { it in specialCharacters }) {
                return true
            }
            return false
        }


        fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
            /**
             * check if password and confirm password match
             */
            if (password == confirmPassword) {
                return true
            }
            return false
        }

}

