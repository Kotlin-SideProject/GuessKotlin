package com.angus.guesskotlin.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Record(
    @NonNull
    @ColumnInfo(name = "nick")
    var nickname : String,
    @NonNull
    var count : Int) {

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0

}

@Entity
class Word(){
    @PrimaryKey
    var name : String =""
    var mean : String = ""
    var star : Int = 0
}