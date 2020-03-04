package com.angus.guesskotlin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record : Record)

    @Query("SELECT * FROM record")
    fun getAll() : List<Record>
}