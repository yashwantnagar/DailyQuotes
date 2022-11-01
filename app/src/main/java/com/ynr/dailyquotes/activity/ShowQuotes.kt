package com.ynr.dailyquotes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.adapter.ShowQuoteAdapter
import com.ynr.dailyquotes.util.QuotesModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class ShowQuotes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_quotes)

        val toolbar : AppCompatTextView = findViewById(R.id.toolbar)
        val showRV : RecyclerView = findViewById(R.id.showRV)

        showRV.layoutManager = LinearLayoutManager(this)


        val quoteList : MutableList<QuotesModel> = mutableListOf()

        try {

//            Log.e("List", "onCreate: aa ")


            val quotesArray = JSONArray(getJSONFromAssets()!!)

//            Log.e("List", "onCreate: ${quotesArray.get(1)}" )

            for (i in 0 until quotesArray.length()) {
               
                // Create a JSONObject for fetching single User's Data
                val jsonObject = quotesArray.getJSONObject(i)
                // Fetch id store it in variable

                val quote = jsonObject.getString("quoteText")
                val author = jsonObject.getString("quoteAuthor")

//                Log.e("List", "onCreate: $quote ")

               val quoteDetail = QuotesModel(quote,author)

                // add the details in the list
                quoteList.add(quoteDetail)

                
            }
            
        } catch (e : JSONException){
            e.stackTrace
        }


        val showQuoteAdapter = ShowQuoteAdapter(this,quoteList)
        showRV.adapter = showQuoteAdapter


    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
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