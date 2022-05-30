package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentEditProfileBinding

class EditProfileFragment : BaseFragment() {
    private var _bn: FragmentEditProfileBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTransparentStatusBarColor(requireContext(), R.color.black, R.color.teal_700, View.STATUS_BAR_VISIBLE)
        _bn = FragmentEditProfileBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
//        changeStatusBar(R.color.black, R.color.teal_700)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

    private fun initView() {
        bn.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        bn.btnDone.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}