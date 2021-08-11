package com.example.mycontactapp

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class ContactAdapter(
    var contact: List<Contact>,
    var contactItemListener: ItemClicked
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(contactView: View) : RecyclerView.ViewHolder(contactView),
        View.OnClickListener {
        var firstLetter: TextView = contactView.findViewById(R.id.firstLetter_tv)
        var contactName : TextView = contactView.findViewById(R.id.contactName_tv)

        init {
            contactView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                contactItemListener.clickItem(position)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_recycler, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var random = Random
        var color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
//        val androidColors = resources.getIntArray(R.array.androidColors)
//        val randomAndroidColor = androidColors[Random.nextInt(androidColors.size)]
        val itemPosition = contact[position]
        holder.apply {
            firstLetter.text = itemPosition.contactSymbol
            contactName.text = itemPosition.contactName
            firstLetter.setBackgroundColor(color)
        }
    }

    override fun getItemCount(): Int {
        return contact.size
    }
}