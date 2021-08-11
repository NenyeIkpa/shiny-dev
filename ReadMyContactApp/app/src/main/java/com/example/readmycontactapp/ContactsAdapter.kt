package com.example.readmycontactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactsAdapter (private val contact: MutableList<MyContacts>): RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.contact_image) as ImageView
        var name: TextView = itemView.findViewById(R.id.contact_name) as TextView
        var mobile: TextView = itemView.findViewById(R.id.contact_mobile) as TextView
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_item_layout, parent, false)
            return ContactViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val contact = contact[position]
            holder.name.text = contact.contactName
            holder.mobile.text = contact.contactNumber
            if (contact.contactImage != null)
                holder.image.setImageBitmap(contact.contactImage)
        }

        override fun getItemCount(): Int = contact.size

}