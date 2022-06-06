package com.cibo.cibo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ActivitySplashBinding
import com.cibo.cibo.manager.PrefsManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val TAG = SplashActivity::class.java.toString()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }

    private fun initViews() {
        countDownTimer()
    }


    fun callIntroActivity(context: Context) {
        val intent = Intent(context, IntroPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun countDownTimer() {
        object : CountDownTimer(2200, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                if (PrefsManager.getInstance(this@SplashActivity)!!.isFirstTime("isFirstTime")) {
                    callIntroActivity(this@SplashActivity)
                    finish()
                } else {
                    callMainActivity(this@SplashActivity)
//                    callSignInActivity(this@SplashActivity)
                    finish()
                }
            }
        }.start()
    }


    private fun setTransparentStatusBar() {
       window.decorView.systemUiVisibility =
            ContextCompat.getColor(this, R.color.white) //  set status text dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.main_red) // set status bar color
    }


}



