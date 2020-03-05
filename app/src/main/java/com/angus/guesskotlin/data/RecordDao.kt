package com.angus.guesskotlin.data

import androidx.room.*

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record : Record)

    @Query("SELECT * FROM record")
    suspend fun getAll() : List<Record>

    @Delete
    suspend fun delete(record: Record)
}