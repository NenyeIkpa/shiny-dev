package com.example.mycontactapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ContactDetails : AppCompatActivity() {

    lateinit var name: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var callButton: ImageButton
    private lateinit var deleteButton: ImageButton
    private lateinit var editButton: ImageButton
    private lateinit var shareButton: ImageButton
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        name = findViewById(R.id.name_contactDetails)
        phoneNumber = findViewById(R.id.phone_contactDetails)
        callButton = findViewById(R.id.callButton)
        deleteButton = findViewById(R.id.deleteButton)
        editButton = findViewById(R.id.editButton)
        shareButton = findViewById(R.id.shareButton)
        databaseRef = FirebaseDatabase.getInstance().getReference("Contacts")

        name.text = intent.getStringExtra("name")
        phoneNumber.text = intent.getStringExtra("phoneNumber")

        callButton.setOnClickListener {
            checkPermission()
        }

        shareButton.setOnClickListener {
            shareContact()
        }

        deleteButton.setOnClickListener {
            askOptionBeforeDelete()
        }


        editButton.setOnClickListener {
            editContact()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, ContactList::class.java)
        startActivity(intent)
    }

//  methods for obtaining permission to call,making a call, sharing, deleting and editing contact
private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)              // Permission is not granted
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE),
                42)
        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
//                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
                Snackbar.make(findViewById(R.id.callButton),
                    R.string.call_permission_granted, Snackbar.LENGTH_LONG).show()
            } else {

                // permission denied
                // functionality disabled
                Snackbar.make(findViewById(R.id.callButton),
                    R.string.call_permission_denied, Snackbar.LENGTH_LONG).show()
            }
            return
        }
    }

    private fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phoneNumber.text}"))
        startActivity(intent)
    }

    private fun askOptionBeforeDelete() {
        val builder = AlertDialog.Builder(this)
        // set message, title, and icon
        builder.apply {
            this
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete?")
                .setIcon(R.drawable.ic_baseline_delete_24)
//                performing positive action
                .setPositiveButton("Delete")
                { dialog, which ->
                    //your deleting code
                    deleteContact()
                    dialog.dismiss()
                }
//                performing negative action
                .setNegativeButton("Cancel")
                { dialog, which -> dialog.dismiss() }
//             create the alert dialog
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun shareContact() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "${name.text} ${phoneNumber.text}") // put the name of the contact here
        startActivity(Intent.createChooser(intent,"share to: "))
    }


    private fun deleteContact() {
        val fullName = name.text.trim().toString()
        databaseRef.child(fullName).setValue(null)

        val intent = Intent(this, ContactList::class.java)
        startActivity(intent)
    }

    private fun editContact() {
        val fullName = name.text.trim().toString()
        val phoneNum = phoneNumber.text.toString()
        val intent = Intent(this, EditContact::class.java)
        intent.putExtra("name", fullName)
        intent.putExtra("phone", phoneNum)
        startActivity(intent)
    }
    }
