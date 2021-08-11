package com.example.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddContact : AppCompatActivity() {

    lateinit var nameView: EditText
    lateinit var phoneNumView: EditText
    lateinit var saveButton: Button
    lateinit var fireDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)


         nameView = findViewById(R.id.addContactName_et)
        phoneNumView = findViewById(R.id.addContactPhone_et)
        saveButton = findViewById(R.id.saveButton)
        fireDatabase = FirebaseDatabase.getInstance().getReference("Contacts")

// click event after inputting details of contact to add
        saveButton.setOnClickListener{
            val fullName = nameView.text?.trim().toString()
            val phoneNumber = phoneNumView.text?.trim().toString()
            val id = ContactObject.getFirstLetterOfName(fullName)

            fireDatabase.child(fullName).setValue(
                Contact(id,fullName,phoneNumber)
            )

            val intent = Intent(this, ContactDetails::class.java)
            intent.putExtra("name", fullName)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        }





    }
}