package com.ynr.dailyquotes

import androidx.lifecycle.LiveData
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDatabase

class QuotesRepository(private val quoteDatabase: QuoteDatabase) {

    fun getAllQuotes() : LiveData<List<Quote>> = quoteDatabase.quoteDao().getAllQuotes()

    suspend fun insertQuotes(quote: Quote) = quoteDatabase.quoteDao().insertQuote(quote)

    suspend fun updateQuotes(quote: Quote) = quoteDatabase.quoteDao().updateQuote(quote)

    suspend fun deleteQuotes(quote: Quote) = quoteDatabase.quoteDao().deleteQuote(quote)


}