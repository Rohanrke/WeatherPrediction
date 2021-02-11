package com.rohan.weatherprediction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.rohan.weatherprediction.base.navigation.Navigator
import com.rohan.weatherprediction.feature.home.HomeActivity
import com.rohan.weatherprediction.preferences.BaseSharedPreferences
import org.koin.android.ext.android.inject

private const val SPLASH_TIME = 2000L

class MainActivity : AppCompatActivity() {

    private val baseAppPreferences: BaseSharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (TextUtils.isEmpty(baseAppPreferences.getSavedCity())) {
            Handler(Looper.getMainLooper()).postDelayed({
                launchHomeActivity()
            }, SPLASH_TIME)

        } else {
            launchHomeActivity()
        }
    }

    private fun launchHomeActivity() {
        Navigator.navigateToActivity(this, HomeActivity::class.java)
        finish()
    }

}