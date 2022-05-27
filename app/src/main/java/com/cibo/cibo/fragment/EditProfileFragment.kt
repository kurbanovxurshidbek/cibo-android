package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        requireActivity().window.getDecorView()
            .setSystemUiVisibility(ContextCompat.getColor(requireContext(), R.color.black)) //  set status text dark
       requireActivity().window.setStatusBarColor(ContextCompat.getColor(requireContext(),
            R.color.teal_700)) // set status bar color
        _bn = FragmentEditProfileBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        val btn_back = bn.btnBack
        val btn_done = bn.btnDone
        val et_name = bn.etName
        val et_number = bn.etNumber


        btn_back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btn_done.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}