package com.cibo.cibo.utils

import android.app.Application
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.drawable.Drawable
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.cibo.cibo.model.ScreenSize


object Utils {

    fun Context.getMyDrawable(id: Int): Drawable? = ResourcesCompat.getDrawable(resources, id, null)

    fun getDeviceID(context: Context):String{
        val device_id: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return device_id
    }

    fun screenSize(context: Application): ScreenSize {
        val displayMetrics = DisplayMetrics()
        val windowsManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth = displayMetrics.widthPixels
        val deviceHeight = displayMetrics.heightPixels
        return ScreenSize(deviceWidth, deviceHeight)
    }

}

