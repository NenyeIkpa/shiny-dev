package com.example.mycontactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ContactList : AppCompatActivity(), ItemClicked {
    private lateinit var database:DatabaseReference
    private lateinit var contactRV:RecyclerView
    private lateinit var  contactList:MutableList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)
        contactList = mutableListOf()
        contactRV = findViewById(R.id.contactList_rv)
        getContactInformationFromFirebase()

        contactRV.layoutManager = LinearLayoutManager(this)

// set floating action button to add contact activity on click
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }

    }

//    save data to database
    private fun getContactInformationFromFirebase() {
        database = FirebaseDatabase.getInstance().getReference("Contacts")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val fullName = i.child("contactName").value
                        val fullSymbol = i.child("contactSymbol").value
                        val fullNumber = i.child("phoneNumber").value
                       contactList.add(
                            Contact(
                                fullSymbol.toString(), fullName.toString(),
                                fullNumber.toString()
                            )
                        )
                    }

                    contactRV.adapter = ContactAdapter(contactList, this@ContactList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun clickItem(position: Int) {
        val intent = Intent(this, ContactDetails::class.java)
        intent.putExtra("name", contactList[position].contactName)
        intent.putExtra("phoneNumber", contactList[position].phoneNumber)
        startActivity(intent)
    }
}
