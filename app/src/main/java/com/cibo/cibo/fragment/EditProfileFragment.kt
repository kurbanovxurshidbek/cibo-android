package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentEditProfileBinding
import com.cibo.cibo.manager.PrefsManager
import com.google.android.material.textfield.TextInputEditText

class EditProfileFragment : BaseFragment() {
    private var _bn: FragmentEditProfileBinding? = null
    private val bn get() = _bn!!

    lateinit var et_name: TextInputEditText
    lateinit var et_number: TextInputEditText
    lateinit var et_surname: TextInputEditText
    lateinit var et_birthday: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setTransparentStatusBarColor(requireContext(),
            R.color.black,
            R.color.white,
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
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
        bn.tvFullname.text = PrefsManager.getInstance(requireContext())!!.getData("name")
        bn.tvPhoneNumber.text = PrefsManager.getInstance(requireContext())!!.getData("number")

        bn.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        bn.btnDone.setOnClickListener {
            val newName = bn.tvFullname.text.toString()
            val newPhoneNumber = bn.tvPhoneNumber.text.toString()

            if (newPhoneNumber.length == 18 && newName.isNotEmpty()) {
                val args = Bundle()
                args.putString("name", newName)
                args.putString("number", newPhoneNumber)
                setFragmentResult("user", args)

                findNavController().navigateUp()
            } else {
                if (newPhoneNumber.length < 18) {
                    toaster("Invalid number")
                    val vibe: Vibrator =
                        activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(150)
                    bn.textInputLayoutNumber.boxStrokeColor =
                        ContextCompat.getColor(requireContext(), R.color.main_red)
                }

                if (newName.isEmpty()) {
                    toaster("Please enter your name")
                    val vibe: Vibrator =
                        activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibe.vibrate(150)
                    bn.textInputLayoutName.boxStrokeColor =
                        ContextCompat.getColor(requireContext(), R.color.main_red)
                }

            }
        }

        changeNameAndPhoneNumber()
    }

    @SuppressLint("SetTextI18n")
    private fun changeNameAndPhoneNumber() {
        et_name = bn.textInputName
        et_surname = bn.textInputSurName
        et_number = bn.textInputNumber
        et_birthday = bn.textInputBirthDate


        et_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn.tvFullname.text = et_name.text.toString()
                if (bn.tvFullname.text.isEmpty()) {
                    bn.tvFullname.text =
                        PrefsManager.getInstance(requireContext())!!.getData("name")
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })



        et_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn.tvPhoneNumber.text = bn.textInputNumber.text

                if (et_number.text.toString() == "+998(") {
                    bn.tvPhoneNumber.text =
                        PrefsManager.getInstance(requireContext())!!.getData("number")
                }



                if (et_number.text!!.length < 5) {
                    et_number.setText("+998(")
                    editLastCursor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        et_number.setOnFocusChangeListener { View, hasFocos ->
            if (hasFocos) {
                if (et_number.text!!.isEmpty()){
                    et_number.setText("${et_number.text}+998(")
                    editLastCursor()
                }

                if (!et_number.text!!.contains("+998(") ||
                    et_number.text!![0].toString() != "+"
                ) {
                    et_number.setText("+998(")
                    editLastCursor()
                }

            }else{
                if (et_number.text!!.toString() == "+998(" )
                    et_number.text!!.clear()
            }
        }




        et_number.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {

                if (et_number.text!![et_number.text!!.length - 1].toString() == " ") {

                    et_number.setText(et_number.text!!.substring(0, et_number.text!!.length - 1))
                    editLastCursor()

                } else if (et_number.text?.get(et_number.text?.length!! - 1).toString() == ")") {

                    et_number.setText(et_number.text!!.substring(0, et_number.text!!.length - 1))
                    editLastCursor()

                }

            } else {
                if (et_number.text!!.length == 7) {
                    et_number.setText("${et_number.text})")
                    editLastCursor()
                }

                if (et_number.text!!.length == 8) {
                    et_number.setText("${et_number.text} ")
                    editLastCursor()
                }

                if (et_number.text!!.length == 12) {
                    et_number.setText("${et_number.text} ")
                    editLastCursor()
                }

                if (et_number.text!!.length == 15) {
                    et_number.setText("${et_number.text} ")
                    editLastCursor()
                }
            }
            false
        }


    }


    private fun editLastCursor() {
        bn.textInputNumber.setSelection(bn.textInputNumber.length())
    }


}

