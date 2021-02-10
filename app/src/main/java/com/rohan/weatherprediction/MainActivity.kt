package com.rohan.weatherprediction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rohan.weatherprediction.feature.home.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {

            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            this@MainActivity.finish()
        }, 2000)
    }

}