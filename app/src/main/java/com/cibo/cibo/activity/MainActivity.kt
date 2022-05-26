package com.cibo.cibo.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ActivityMainBinding
import com.cibo.cibo.fragment.RestaurantFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.chipNavigation.setItemSelected(R.id.scan, true)
        supportFragmentManager.beginTransaction().replace(R.id.container, RestaurantFragment())
            .commit()
    }

    @SuppressLint("ObsoleteSdkInt")
    fun updateStatusBarColor(color: String?) { // Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }

}