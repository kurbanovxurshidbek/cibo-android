package com.cibo.cibo.activity

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ActivityMainBinding
import com.cibo.cibo.fragment.RestaurantFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.chipNavigation.setItemSelected(R.id.scan,true)
        supportFragmentManager.beginTransaction().replace(R.id.container, RestaurantFragment()).commit()
    }

   /* override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                val outRect = Rect()
                bottomSheet.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }*/

}