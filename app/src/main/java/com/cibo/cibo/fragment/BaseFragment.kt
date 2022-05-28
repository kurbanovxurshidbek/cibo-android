package com.cibo.cibo.fragment

import android.Manifest
import android.os.Bundle
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

}