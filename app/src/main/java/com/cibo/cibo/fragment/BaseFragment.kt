package com.cibo.cibo.fragment

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cibo.cibo.R
import com.cibo.cibo.model.Item
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.Serializable

open class BaseFragment : Fragment() {

    fun openFragment(resId: Int, data: Serializable) {
        val args = Bundle()
        args.putSerializable("productAbout", data)
        findNavController().navigate(resId, args)
    }

    /*fun changeStatusBar(textColor: Int, barColor: Int) {
        //  set status text dark
        requireActivity().window.decorView.systemUiVisibility =
            if (barColor == R.color.white) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else ContextCompat.getColor(requireContext(), textColor)

        // set status bar color
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), barColor)
    }*/

}