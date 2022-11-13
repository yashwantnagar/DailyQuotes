package com.ynr.dailyquotes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("select * from quote_table")
    suspend fun getAllQuotes() : List<Quote>

}