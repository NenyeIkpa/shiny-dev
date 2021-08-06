package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegisteredPerson : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered_person)
        val registeredPerson = findViewById<TextView>(R.id.registeredPerson)

        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person
        registeredPerson.text = person.toString()

    }
}