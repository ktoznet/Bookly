package com.example.bookly.presentor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bookly.R
import com.example.bookly.databinding.ActivityMainBinding
import com.example.bookly.fragment.MAinFragment

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction().replace(R.id.place_holder_time, MAinFragment.newInstance())
            .commit()
    }


}