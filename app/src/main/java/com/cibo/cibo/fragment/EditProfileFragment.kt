package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
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
            val name = bn.tvFullname.text.toString()
            val phoneNumber = bn.tvPhoneNumber.text.toString()

            val args = Bundle()
            args.putString("name", name)
            args.putString("number", phoneNumber)

            setFragmentResult("user", args)
            findNavController().navigateUp()
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

//                et_surname.addTextChangedListener(object : TextWatcher{
//                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        et_name.text.toString() + " " + et_surname.text.toString()
//                    }
//
//                    override fun afterTextChanged(p0: Editable?) {}
//
//                })
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        et_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bn.tvPhoneNumber.text = bn.textInputNumber.text
                if (!et_number.text!!.contains("+998(") ||
                    et_number.text!![0].toString() != "+"
                ) {
                    et_number.setText("+998(")
                    editLastCursor()
                }
                if (et_number.text!!.length < 5) {
                    et_number.setText("+998(")
                    editLastCursor()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        et_number.onFocusChangeListener = View.OnFocusChangeListener { _, p1 ->
            if (p1) {
                if (et_number.text!!.isEmpty())
                    et_number.setText("${et_number.text}+998(")
                editLastCursor()
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

