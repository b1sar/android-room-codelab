package com.cebrailyilmaz.roomcodelab.data.dao

import androidx.lifecycle.LiveData
import com.cebrailyilmaz.roomcodelab.data.Person

class PersonRepository(private val personDao: PersonDao) {
    val allPeople: LiveData<List<Person>> = personDao.getAllOrderedByAge()

    suspend fun insert(person: Person) {
        personDao.create(person)
    }
}