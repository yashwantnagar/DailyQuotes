package com.ynr.dailyquotes.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.adapter.QuoteSectionAdapter

class QuoteSectionActivity : AppCompatActivity() {

    val TAG = "TAG";

    override fun onCreate(savedInstanceState: Bundle?) {

        // Handle the splash screen transition.
//        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_section)

        Log.d(TAG, "onCreate: 123 ")

//        splashScreen.setKeepOnScreenCondition{ true }
//        startSomeNextActivity()
//        finish()


        val toolBar : Toolbar = findViewById(R.id.toolbar)
        val mainRV : RecyclerView = findViewById(R.id.mainRV)
//        val progressBar : ProgressBar = findViewById(R.id.progressBar)

        toolBar.setTitle("Quotes")
        mainRV.layoutManager = LinearLayoutManager(this)
//        mainRV.layoutManager = GridLayoutManager(this,2)


        val quoteSectionAdapter = QuoteSectionAdapter(this)
        mainRV.adapter = quoteSectionAdapter
        
        
        
    }
    
}