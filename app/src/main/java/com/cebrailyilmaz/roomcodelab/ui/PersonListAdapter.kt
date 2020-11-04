package com.cebrailyilmaz.roomcodelab.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cebrailyilmaz.roomcodelab.R
import com.cebrailyilmaz.roomcodelab.data.Person

class PersonListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var people = emptyList<Person>() // Cached copy of words

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personAgeView: TextView = itemView.findViewById(R.id.person_age)
        val personFullName: TextView = itemView.findViewById(R.id.person_fullname)
        val personId: TextView = itemView.findViewById(R.id.person_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_person_item, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val current = people[position]
        holder.personAgeView.text = current.age.toString()
        holder.personFullName.text = "${current.firstName} ${current.lastName}"
        holder.personId.text = current.id.toString()
    }

    internal fun setPeople(people: List<Person>) {
        this.people = people
        notifyDataSetChanged()
    }

    override fun getItemCount() = people.size
}