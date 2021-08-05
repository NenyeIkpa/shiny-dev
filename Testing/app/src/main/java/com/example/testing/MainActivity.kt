package com.example.testing

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var registerButton: Button
    private lateinit var fullName: EditText
    private lateinit var phoneNum: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        fullName = findViewById(R.id.fullName_et)
        phoneNum = findViewById(R.id.phoneNum_et)
        email = findViewById(R.id.email_et)
        password = findViewById(R.id.password_et)
        confirmPassword = findViewById(R.id.confirmPassword_et)
        val sex = spinner.selectedItem


        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(
            this,
            R.layout.spinner_list, resources.getStringArray(R.array.sex_array)
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }

            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    Toast.makeText(
                        this@MainActivity,
                        adapterView?.getItemAtPosition(position).toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {


            val person = Person(fullName.text.toString(), phoneNum.text.toString(),
                email.text.toString(), sex.toString(), password.text.toString(),
                confirmPassword.text.toString())

            if (RegistrationInputValidation.validateName(fullName.text.toString())&&
                RegistrationInputValidation.validatePhoneNumber(phoneNum.text.toString()) &&
                RegistrationInputValidation.validateEmail(email.text.toString())&&
                RegistrationInputValidation.validateConfirmPassword(
                    password.text.toString(),
                    confirmPassword.text.toString()
                )){
                 val intent = Intent(this, RegisteredPerson::class.java)
                intent.putExtra("EXTRA_PERSON", person)

                startActivity(intent)
                }





                if (RegistrationInputValidation.validateAnyInputIsEmpty(
                        fullName.text.toString(), phoneNum.text.toString(),
                        email.text.toString(), password.text.toString(),
                        confirmPassword.text.toString()
                    )
                ) {
                    if (fullName.text.toString().isEmpty()) fullName.error = "Please enter your fullname"
                    if (phoneNum.text.toString().isEmpty()) phoneNum.error = "Please enter your phone number"
                    if (email.text.toString().isEmpty()) email.error = "Please enter your email"
                    if (password.text.toString().isEmpty()) password.error = "Please create a password"
                    if (confirmPassword.text.toString().isEmpty()) confirmPassword.error = "Please confirm password"

                } else  {

                    if (!(RegistrationInputValidation.validateName(fullName.text.toString()))) {
                        fullName.error = "Please enter first and last name."
                    }
                    if (!(RegistrationInputValidation.validatePhoneNumber(phoneNum.text.toString()))) {
                        phoneNum.error = "Please enter a nigerian phone number."
                    }
                    if (!(RegistrationInputValidation.validateEmail(email.text.toString()))) {
                        email.error = "Please enter a valid email"
                    }
                    if (!(RegistrationInputValidation.validatePassword(password.text.toString()))) {
                        password.error =
                            "Password should have more than 5 characters and must contain " +
                                    "a number, an alphabet and a special character."
                    }
                    if (!(RegistrationInputValidation.validateConfirmPassword(
                            password.text.toString(),
                            confirmPassword.text.toString()
                        ))
                    ) {
                        confirmPassword.error = "Password does not match"
                    }


                }
        }


    }
}
