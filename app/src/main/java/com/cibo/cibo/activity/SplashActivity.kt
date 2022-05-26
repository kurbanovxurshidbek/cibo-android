package com.cibo.cibo.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
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


        fun callIntroActivity(context: Context){
            val intent = Intent(context,IntroPageActivity::class.java)
            startActivity(intent)
            finish()
        }
        fun callMainActivity(context: Context){
        val intent = Intent(context,MainActivity::class.java)
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
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT

            getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) //  set status text dark
            getWindow().setStatusBarColor(ContextCompat.getColor(this,
                R.color.white)) // set status background white
        }
    }


}



