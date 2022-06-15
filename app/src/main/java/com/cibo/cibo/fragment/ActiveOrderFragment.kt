package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentActiveOrderBinding
import com.cibo.cibo.databinding.FragmentEditProfileBinding

class ActiveOrderFragment:BaseFragment() {

    private var _bn: FragmentActiveOrderBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentActiveOrderBinding.inflate(inflater, container, false)
        return bn.root
    }
}