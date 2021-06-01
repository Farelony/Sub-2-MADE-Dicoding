package com.example.tmdb.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tmdb.databinding.ActivitySplashScreenBinding
import com.example.tmdb.home.HomeScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private var handler = Handler()
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(mainLooper)
        handler.postDelayed({
            Intent(this, HomeScreenActivity::class.java).also { startActivity(it) }
            finish()
        },3000)
    }
}