package com.cebrailyilmaz.roomcodelab.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cebrailyilmaz.roomcodelab.data.Person
import com.cebrailyilmaz.roomcodelab.data.dao.PersonRepository
import com.cebrailyilmaz.roomcodelab.data.db.PersonRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository
    val allPeeople: LiveData<List<Person>>

    init {
        val personDao = PersonRoomDatabase.getDatabase(application, viewModelScope).personDao()
        repository = PersonRepository(personDao)
        allPeeople = repository.allPeople
    }

    fun insert(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }
}