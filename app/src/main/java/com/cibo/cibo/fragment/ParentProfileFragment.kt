package com.cibo.cibo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cibo.cibo.R

class ParentProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initView()
        return inflater.inflate(R.layout.fragment_parent_profile, container, false)
    }

    private fun initView() {

    }
}