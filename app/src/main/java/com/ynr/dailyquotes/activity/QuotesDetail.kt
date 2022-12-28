package com.ynr.dailyquotes.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDao
import com.ynr.dailyquotes.database.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesDetail : AppCompatActivity(), View.OnClickListener {

    private lateinit var quotes : String
    private lateinit var author : String

    private lateinit var quoteDao: QuoteDao

    private val TAG = "Quote";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_detail)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        val quotesTV : AppCompatTextView = findViewById(R.id.quotes)
        val authorTV : AppCompatTextView = findViewById(R.id.author)

        val btnCopy : AppCompatTextView = findViewById(R.id.copy)
        val btnSave : AppCompatTextView = findViewById(R.id.save)
        val btnShare : AppCompatTextView = findViewById(R.id.share)

//        val actionBar = supportActionBar
//        actionBar?.setDisplayHomeAsUpEnabled(true)


        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

         quotes = intent.getStringExtra("quotes").toString()
         author = intent.getStringExtra("author").toString()


        quotesTV.text = quotes
        authorTV.setText(author)


        btnCopy.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        btnShare.setOnClickListener(this)

        quoteDao = QuoteDatabase.getDatabase(this).quoteDao()

        lifecycleScope.launch(Dispatchers.Main){

            getAllQuote()

        }

    }


    private suspend fun getAllQuote() {

        // Query

        val quoteList = quoteDao.getAllQuotes()

        Log.e(TAG," *****   ${quoteList.size} Quotes there ***** ")

        for(quote in quoteList){

            Log.e(TAG,"id: ${quote.id} name: ${quote.quote} " +
                    "author: ${quote.author}")

        }


        // Update

//        Log.e("MyTAG","*****      Updating a book      **********")
//        quoteDao.updateQuote(Quote(1,"PHPUpdated","Mike"))
//
//        // Query
//
//        val books2 = quoteDao.getAllQuotes()
//        Log.e("MyTAG","*****   ${books2.size} books there *****")
//        for(book in books2){
//            Log.e("MyTAG","id: ${book.id} name: ${book.quote} author: ${book.author}")
//        }


        // delete

//        Log.e("MyTAG","*****       Deleting a book      **********")
//        quoteDao.deleteQuote(Quote(2,"Kotlin","Amelia"))
//
//        // Query
//        val books3 = quoteDao.getAllQuotes()
//        Log.e(TAG,"*****   ${books3.size} books there *****")
//
//        for(book in books3){
//            Log.e("MyTAG","id: ${book.id} name: ${book.quote} author: ${book.author}")
//        }


    }


    override fun onClick(v: View?) {

        when(v?.id){

          R.id.copy -> copyQuotes()
          R.id.save -> saveQuotes()
          R.id.share -> shareQuotes()

        }

    }

    private fun copyQuotes() {

        Log.e("Quotes", "copyQuotes: $quotes" )

        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        // When setting the clip board text.
        clipboardManager.setPrimaryClip(ClipData.newPlainText("", quotes))
        // Only show a toast for Android 12 and lower.

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
            Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show()

    }

    private fun saveQuotes() {

        lifecycleScope.launch (Dispatchers.IO){

            // Insert Quote

            Log.e(TAG, " ***** saveQuotes: ***** " )

            quoteDao.insertQuote(Quote(0,quotes,author))


            // Query

            val quoteList = quoteDao.getAllQuotes()

            Log.e(TAG," *****   ${quoteList.size} Quotes there ***** ")

            for(quote in quoteList){

                Log.e(TAG,"id: ${quote.id} name: ${quote.quote} " +
                        "author: ${quote.author}")

            }

        }


        Toast.makeText(this,"Save",Toast.LENGTH_SHORT).show()


    }


    private fun shareQuotes() {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, quotes)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }


    // this event will enable the back
    // function to the button on press
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }


}