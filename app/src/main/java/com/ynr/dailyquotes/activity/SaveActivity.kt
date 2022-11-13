package com.ynr.dailyquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.adapter.SaveQuoteAdapter
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDao
import com.ynr.dailyquotes.database.QuoteDatabase
import com.ynr.dailyquotes.database.QuoteDbModel
import com.ynr.dailyquotes.util.DeleteQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveActivity : AppCompatActivity(), DeleteQuote {

    private lateinit var toolbar: AppCompatTextView
    private lateinit var saveRV : RecyclerView
    private lateinit var btnBack : AppCompatImageButton

    private lateinit var quoteDao: QuoteDao

    private lateinit var deleteQuote : DeleteQuote

    val TAG = "Save"

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        toolbar  = findViewById(R.id.toolbar)
        saveRV  = findViewById(R.id.saveRV)
        btnBack  = findViewById(R.id.btnBack)


        saveRV.layoutManager = LinearLayoutManager(this)


        quoteDao = QuoteDatabase.getDatabase(this).quoteDao()

//        val quoteAllList : ArrayList<QuoteDbModel> = mutableListOf()

        val quoteAllList  = ArrayList<QuoteDbModel>()

        val getList : ArrayList<QuoteDbModel> = getAllQuote(quoteAllList)

        deleteQuote = this

        val saveQuoteAdapter = SaveQuoteAdapter(this,getList,deleteQuote)
        saveRV.adapter = saveQuoteAdapter


//        val saveQuoteAdapter = SaveQuoteAdapter(this)
//        saveRV.adapter = saveQuoteAdapter

    }

    private fun getAllQuote(quoteAllList : ArrayList<QuoteDbModel>): ArrayList<QuoteDbModel> {


        lifecycleScope.launch(Dispatchers.IO){

            // Query

            val quoteList = quoteDao.getAllQuotes()

            Log.e(TAG," *****   ${quoteList.size} Quotes there ***** ")

            for(quote in quoteList){

                Log.e(TAG,"id: ${quote.id} name: ${quote.quote} " +
                        "author: ${quote.author}")

                quoteAllList.add(
                    QuoteDbModel(
                        quote.id,quote.quote,quote.author)
                )

            }

        }

        return  quoteAllList

    }

    override fun quote( quote : Quote) {

        Log.e(TAG, "quote: ${quote.id}" )

        lifecycleScope.launch(Dispatchers.IO) {

            quoteDao.deleteQuote(Quote(quote.id,quote.quote,quote.author))

        }

    }


}