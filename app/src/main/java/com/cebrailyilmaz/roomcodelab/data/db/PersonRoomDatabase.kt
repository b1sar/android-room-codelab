package com.cebrailyilmaz.roomcodelab.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cebrailyilmaz.roomcodelab.data.dao.PersonDao
import com.cebrailyilmaz.roomcodelab.data.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Person::class), version = 1, exportSchema = false)
abstract class PersonRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class PersonRoomDatabaseCallBack(val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val dbDao = database.personDao()
                    populateDbWithInitialData(dbDao)
                }
            }
        }

        suspend fun populateDbWithInitialData(dao: PersonDao) {
            arrayListOf<Person>(
                Person(1, "John", "Doe", 34),
                Person(2, "Ian", "Boe", 58),
                Person(3, "David", "Goe", 12),
                Person(4, "Jack", "Joe", 87),
                Person(5, "Duck", "DuckGo", 28)
            ).forEach {
                dao.create(it)
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PersonRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PersonRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(PersonRoomDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}