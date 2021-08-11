package com.example.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditContact: AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editPhone: EditText
    lateinit var fireDatabase: DatabaseReference
    private lateinit var saveEditButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        editName =findViewById(R.id.editContactName_et)
        editPhone= findViewById(R.id.editContactPhone_et)
        saveEditButton = findViewById(R.id.saveEditButton)
        fireDatabase = FirebaseDatabase.getInstance().getReference("Contacts")

        val fullName = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")

        editName.setText(fullName)
        editPhone.setText(phone)

//click event on save of contact details
        saveEditButton.setOnClickListener{
            val phoneNumber = editPhone.text.toString()
            val newName = editName.text.toString()
            val id = ContactObject.getFirstLetterOfName(newName)

            fireDatabase.child(fullName.toString()).setValue(
                Contact(id,newName,phoneNumber)
            )

            val intent = Intent(this, ContactDetails::class.java)
            intent.putExtra("name", newName)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        }

    }
}