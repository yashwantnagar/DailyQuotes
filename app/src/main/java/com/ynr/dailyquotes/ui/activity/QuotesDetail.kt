package com.ynr.dailyquotes.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ynr.dailyquotes.QuotesRepository
import com.ynr.dailyquotes.QuotesViewModel
import com.ynr.dailyquotes.QuotesViewModelFactory
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.database.Quote
import com.ynr.dailyquotes.database.QuoteDatabase
import com.ynr.dailyquotes.databinding.ActivityQuotesDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesDetail : AppCompatActivity(), View.OnClickListener {

    private lateinit var quotes : String
    private lateinit var author : String

    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var quoteDatabase: QuoteDatabase
    private lateinit var repository: QuotesRepository
    private lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding : ActivityQuotesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_detail)

        binding = ActivityQuotesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        quotes = intent.getStringExtra("quotes").toString()
        author = intent.getStringExtra("author").toString()

        quoteDatabase = QuoteDatabase.getDatabase(this)
        repository = QuotesRepository(quoteDatabase)
        factory = QuotesViewModelFactory(repository)

        quotesViewModel = ViewModelProvider(this,factory)[QuotesViewModel::class.java]

        binding.quotes.text = quotes
        binding.author.text = author


        binding.bottom.copy.setOnClickListener(this)
        binding.bottom.save.setOnClickListener(this)
        binding.bottom.share.setOnClickListener(this)
        binding.toolbar.setOnClickListener(this)


    }


    override fun onClick(v: View?) {

        when(v?.id){

          R.id.copy -> copyQuotes()
          R.id.save -> saveQuotes()
          R.id.share -> shareQuotes()
          R.id.toolbar -> onBackPressedDispatcher.onBackPressed()

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

            quotesViewModel.insertQuotes(Quote(0,quotes,author))


        }

        val quotelist = quotesViewModel.getAllQuotes()


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


}