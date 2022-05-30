package com.cibo.cibo.fragment


import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){
    fun setTransparentStatusBarColor(context: Context, textColor: Int?, statusColor: Int?, lightStatus: Int?){
        requireActivity().window.getDecorView()
            .setSystemUiVisibility(ContextCompat.getColor(context, textColor!!)) //  set status text dark
        requireActivity().window.setStatusBarColor(ContextCompat.getColor(context,
            statusColor!!)) // set status bar color
        requireActivity().window.getDecorView()
            .setSystemUiVisibility(lightStatus!!)
    }

}
