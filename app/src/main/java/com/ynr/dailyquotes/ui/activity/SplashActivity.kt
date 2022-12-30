package com.ynr.dailyquotes.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animateRotate = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding.img.startAnimation(animateRotate)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ShowQuotes::class.java)
            startActivity(intent)
            finish()
        },3000)

    }
}