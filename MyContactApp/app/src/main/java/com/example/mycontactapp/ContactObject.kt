package com.example.mycontactapp
// singleton
object ContactObject {

    var contactList: MutableList<Contact> = mutableListOf()

    fun getFirstLetterOfName(contactName:String):String{
        return contactName[0].toUpperCase().toString()
    }


}