package com.ynr.dailyquotes.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.databinding.ActivityShowQuotesBinding
import com.ynr.dailyquotes.ui.adapter.ShowQuoteAdapter
import com.ynr.dailyquotes.util.QuotesModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class ShowQuotes : AppCompatActivity() {

    private lateinit var binding : ActivityShowQuotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_quotes)

        binding = ActivityShowQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showRV.layoutManager = LinearLayoutManager(this)


        val quoteList : MutableList<QuotesModel> = mutableListOf()

        try {

            val quotesArray = JSONArray(getJSONFromAssets()!!)

            for (i in 0 until quotesArray.length()) {

                // Create a JSONObject for fetching single User's Data
                val jsonObject = quotesArray.getJSONObject(i)
                // Fetch id store it in variable

                val quote = jsonObject.getString("quoteText")
                val author = jsonObject.getString("quoteAuthor")

               val quoteDetail = QuotesModel(quote,author)

                // add the details in the list
                quoteList.add(quoteDetail)


            }

        } catch (e : JSONException){
            e.stackTrace
        }

        val showQuoteAdapter = ShowQuoteAdapter(this,quoteList)
        binding.showRV.adapter = showQuoteAdapter


        binding.toolbar.btnSave.setOnClickListener {

            val intent = Intent(this, SaveActivity::class.java)
            startActivity(intent)

        }

    }


    private fun getJSONFromAssets(): String? {

        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("quotes.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json

    }

}