package com.ynr.dailyquotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class Quote(

    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name = "quote")
    var quote : String,

    @ColumnInfo(name = "author")
    var author : String

)
