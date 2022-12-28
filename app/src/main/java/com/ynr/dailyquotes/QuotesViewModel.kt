package com.ynr.dailyquotes

import androidx.lifecycle.ViewModel
import com.ynr.dailyquotes.database.Quote

class QuotesViewModel(private val quotesRepository : QuotesRepository) : ViewModel() {

    fun getAllQuotes() = quotesRepository.getAllQuotes()

    suspend fun insertQuotes(quote : Quote) = quotesRepository.insertQuotes(quote)

    suspend fun updateQuotes(quote : Quote) = quotesRepository.updateQuotes(quote)

    suspend fun deleteQuotes(quote : Quote) = quotesRepository.deleteQuotes(quote)

}