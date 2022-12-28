package com.ynr.dailyquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuotesViewModelFactory(private val quotesRepository: QuotesRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            QuotesViewModel(this.quotesRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}