package com.ynr.dailyquotes.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.ynr.dailyquotes.R

class QuotesDetail : AppCompatActivity(), View.OnClickListener {

    lateinit var quotes : String
    lateinit var author : String

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
//        Toast.makeText(this,"Copy",Toast.LENGTH_SHORT).show()
    }

    private fun saveQuotes() {
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