package com.cebrailyilmaz.roomcodelab.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(@PrimaryKey(autoGenerate = true) val id: Int, val firstName: String, val lastName: String,  val age: Int)