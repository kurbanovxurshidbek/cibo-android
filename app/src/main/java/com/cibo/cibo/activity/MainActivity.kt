package com.cibo.cibo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ActivityMainBinding
import com.cibo.cibo.fragment.ProfileFragment
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.fragment.ScanFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.chipNavigation.setItemSelected(R.id.scan,true)
        supportFragmentManager.beginTransaction().replace(R.id.container, ProfileFragment()).commit()
    }
}