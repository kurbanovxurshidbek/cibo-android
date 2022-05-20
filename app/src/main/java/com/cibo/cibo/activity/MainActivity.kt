package com.cibo.cibo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.cibo.cibo.adapter.ViewPagerAdapter
import com.cibo.cibo.fragment.HomeFragment
import com.cibo.cibo.fragment.ProfileFragment
import com.cibo.cibo.fragment.ScannerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.toString()
    var index = 0
     var homeFragment = HomeFragment()
     var scannerFragment = ScannerFragment()
     var profileFragment = ProfileFragment()
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager.setCurrentItem(0)
                R.id.navigation_scan -> viewPager.setCurrentItem(1)
                R.id.navigation_profile -> viewPager.setCurrentItem(2)
            }
            true
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                index = position
                bottomNavigationView.getMenu().getItem(index).setChecked(true)
            }

            override fun onPageScrollStateChanged(state: Int) { }
        })

        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(homeFragment)
        adapter.addFragment(scannerFragment)
        adapter.addFragment(profileFragment)
        viewPager.adapter = adapter
    }
}