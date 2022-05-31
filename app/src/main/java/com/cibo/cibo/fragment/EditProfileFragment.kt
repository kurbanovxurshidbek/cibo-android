package com.cibo.cibo.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentEditProfileBinding
import com.google.android.material.textfield.TextInputEditText

class EditProfileFragment : BaseFragment() {
    private var _bn: FragmentEditProfileBinding? = null
    private val bn get() = _bn!!

    lateinit var et_name: TextInputEditText
    lateinit var et_number: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTransparentStatusBarColor(requireContext(), R.color.black, R.color.white, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        _bn = FragmentEditProfileBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
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

        changeNameAndPhoneNumber()
    }

    private fun changeNameAndPhoneNumber() {
        et_name = bn.textInputName

        et_name.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn.tvFullname.text = et_name.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        et_number = bn.textInputNumber

        et_number.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn.tvPhoneNumber.text = et_number.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


}