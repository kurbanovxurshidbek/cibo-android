package com.cibo.cibo.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat


object Utils {

    fun Context.getMyDrawable(id: Int): Drawable? = ResourcesCompat.getDrawable(resources, id, null)



}

