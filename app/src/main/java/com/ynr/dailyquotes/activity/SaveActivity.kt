package com.ynr.dailyquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.adapter.SaveQuoteAdapter
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDao
import com.ynr.dailyquotes.database.QuoteDatabase
import com.ynr.dailyquotes.database.QuoteDbModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var saveRV : RecyclerView

    private lateinit var quoteDao : QuoteDao

    val TAG = "Save"

    private lateinit var saveQuoteAdapter : SaveQuoteAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        toolbar  = findViewById(R.id.toolbar)
        saveRV  = findViewById(R.id.saveRV)

        saveRV.layoutManager = LinearLayoutManager(this)


        quoteDao = QuoteDatabase.getDatabase(this).quoteDao()

        val quoteAllList  = ArrayList<QuoteDbModel>()

        val getList : ArrayList<QuoteDbModel> = getAllQuote(quoteAllList)

        saveQuoteAdapter = SaveQuoteAdapter(this,getList)
        saveRV.adapter = saveQuoteAdapter


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setOnClickListener {
            onBackPressedDispatcher
        }

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


    fun onItemClick(quote : Quote){

        lifecycleScope.launch(Dispatchers.IO){

            quoteDao.deleteQuote(Quote(quote.id,quote.quote,quote.author))

        }

        saveQuoteAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_SHORT).show()
    }


}