package com.cibo.cibo.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.cibo.cibo.R
import com.cibo.cibo.adapter.IntroPageItemAdapter
import com.cibo.cibo.databinding.ActivityIntroPageBinding
import com.cibo.cibo.manager.PrefsManager
import com.cibo.cibo.model.IntroPageItem

class IntroPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroPageBinding
    private var isFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            viewPager.adapter = IntroPageItemAdapter(this@IntroPageActivity, getItems())
            dotsIndicator.setViewPager2(viewPager)
            btnContinue.setOnClickListener {
                viewPager.currentItem = ++viewPager.currentItem
            }
            tvSkip.setOnClickListener {
                viewPager.currentItem = getItems().size - 1
            }
            btnGetStarted.setOnClickListener {
                saveLoggedState()
                callMainActivity(this@IntroPageActivity)
                finish()
            }
        }
        applyPageStateChanges()
    }
    fun callMainActivity(context: Context){
        val intent = Intent(context,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveLoggedState() {
        isFirstTime = false
        PrefsManager.getInstance(this@IntroPageActivity)!!.setFirstTime("isFirstTime", isFirstTime)
    }

    private fun applyPageStateChanges() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (position == getItems().size - 1) {
                        btnContinue.visibility = View.GONE
                        btnGetStarted.visibility = View.VISIBLE
                    } else {
                        btnContinue.visibility = View.VISIBLE
                        btnGetStarted.visibility = View.GONE
                    }

                }
            })
        }
    }

    private fun getItems(): ArrayList<IntroPageItem> {
        val items = ArrayList<IntroPageItem>()
        items.add(
            IntroPageItem(
                R.drawable.scan,
                getString(R.string.str_save_your_time),
                getString(R.string.str_qr_code_description)
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.help,
                getString(R.string.str_description),
                getString(R.string.str_menu_order)
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.menu_phone,
                getString(R.string.str_description),
                getString(R.string.str_menu_order_phone)
            )
        )
        return items
    }
}