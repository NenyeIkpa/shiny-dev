package com.example.readmycontactapp

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
//        declare variables globally
    private var contactList : MutableList<MyContacts> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var displayText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        button = findViewById(R.id.button)
        displayText = findViewById(R.id.deniedPermission_tv)



        // code for recyclerview and adapter and checkPermission
            val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(itemDecoration)
            checkPermission()

        }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)              // Permission is not granted
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                42)
        } else {
            // Permission has already been granted
           getContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                getContacts()
                displayText.visibility = View.GONE
                button.visibility = View.GONE
            } else {

                // permission denied
                // functionality disabled
                    displayText.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
            }
//            set conditions to meet on button click
            button.setOnClickListener { checkPermission()}
            return
        }
    }

//    function to retrieve contacts and their details
        private fun getContacts() {
            val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)

            if (cursor != null) {
                while (cursor.moveToNext()) {
                     val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phoneNumber =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val contact = MyContacts()
                    contact.contactName = name
                    contact.contactNumber = phoneNumber.toString()

                    val inputStream =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                    if (inputStream != null) {
                        contact.contactImage = MediaStore.Images.Media.getBitmap(
                            contentResolver,
                            Uri.parse(inputStream)
                        )
                    }
                    contactList.add(contact)
                }
                recyclerView.adapter = ContactsAdapter(contactList)
                cursor.close()
            }
        }
}