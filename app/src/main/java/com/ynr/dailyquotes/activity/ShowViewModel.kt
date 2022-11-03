package com.ynr.dailyquotes.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.ynr.dailyquotes.util.QuotesModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset

class ShowViewModel (val context : Context) : ViewModel() {


    private var quoteList : MutableList<QuotesModel> = mutableListOf()


    private var index = 0
    private var quoteListArr : Array<QuotesModel> = emptyArray()

    init {

        quoteList = loadDataFromAsset()

        quoteListArr = loadQuotesFromAsset()


    }

    private fun loadQuotesFromAsset(): Array<QuotesModel> {

        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<QuotesModel>::class.java)

//        return quoteListArr

    }



//    fun getQuote() = quoteListArr[index]

    fun getQuote() = quoteList

    fun nextQuote() = quoteListArr[++index]

    fun previousQuote() = quoteListArr[--index]


    private fun loadDataFromAsset(): MutableList<QuotesModel> {

        try {

            val quotesArray = JSONArray(getJSONFromAssets()!!)


            for (i in 0 until quotesArray.length()) {

                // Create a JSONObject for fetching single User's Data
                val jsonObject = quotesArray.getJSONObject(i)
                // Fetch id store it in variable

                val quote = jsonObject.getString("quoteText")
                val author = jsonObject.getString("quoteAuthor")


                val quoteDetail = QuotesModel(quote, author)

                // add the details in the list
                quoteList.add(quoteDetail)


            }

        } catch (e: JSONException) {
            e.stackTrace
        }

        return quoteList

    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = context.assets.open("quotes.json")
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