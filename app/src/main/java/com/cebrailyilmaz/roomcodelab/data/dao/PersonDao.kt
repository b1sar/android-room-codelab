package com.cebrailyilmaz.roomcodelab.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cebrailyilmaz.roomcodelab.data.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(person: Person)

    @Query("select * from person where id=:id")
    fun read(id: Int): LiveData<List<Person>>

    @Query("select * from person order by person.age asc")
    fun getAllOrderedByAge(): LiveData<List<Person>>

    @Query("delete from person")
    fun deleteAll()
}