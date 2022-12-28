package com.ynr.dailyquotes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynr.dailyquotes.QuotesRepository
import com.ynr.dailyquotes.QuotesViewModel
import com.ynr.dailyquotes.QuotesViewModelFactory
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.ui.adapter.SaveQuoteAdapter
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDatabase
import com.ynr.dailyquotes.databinding.ActivitySaveBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveActivity : AppCompatActivity() {

    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var quoteDatabase: QuoteDatabase
    private lateinit var repository: QuotesRepository
    private lateinit var factory: ViewModelProvider.Factory

    val TAG = "Save"

    private lateinit var binding : ActivitySaveBinding

    private lateinit var saveQuoteAdapter : SaveQuoteAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveRV.layoutManager = LinearLayoutManager(this)

        quoteDatabase = QuoteDatabase.getDatabase(this)
        repository = QuotesRepository(quoteDatabase)
        factory = QuotesViewModelFactory(repository)

        quotesViewModel = ViewModelProvider(this,factory)[QuotesViewModel::class.java]


        CoroutineScope(Dispatchers.Main).launch {

            quotesViewModel.getAllQuotes().observe(this@SaveActivity){

                saveQuoteAdapter = SaveQuoteAdapter(this@SaveActivity,it)
                binding.saveRV.adapter = saveQuoteAdapter

            }

        }


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setOnClickListener {
            onBackPressedDispatcher
        }

    }

   /* private fun getAllQuote(quoteAllList : ArrayList<QuoteDbModel>): ArrayList<QuoteDbModel> {


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

    }*/


    fun onItemClick(quote : Quote){

        lifecycleScope.launch(Dispatchers.IO){

            quotesViewModel.deleteQuotes(Quote(quote.id,quote.quote,quote.author))
            
        }

//        saveQuoteAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Quotes Deleted",Toast.LENGTH_SHORT).show()


    }


}