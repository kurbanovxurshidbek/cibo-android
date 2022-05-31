package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentLoginBinding

import com.cibo.cibo.utils.Utils.getMyDrawable


class LoginFragment : BaseFragment() {

    private var _bn: FragmentLoginBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentLoginBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneEditTextManager()
        continueButtonManager()
    }



    @SuppressLint("UseCompatLoadingForDrawables")
    private fun continueButtonManager() {
        bn.phoneNumberEdt.apply {
            bn.continueBtn.setOnClickListener {
                when {
                    text!!.length > 17 -> {
                        inputLayoutBoxDisable()

                        findNavController().navigate(
                            R.id.action_loginFragment_to_confirmationFragment,
                            bundleOf("phoneNumber" to text.toString())
                        )
                    }
                    else -> {
                        inputLayoutBoxEnable()
                    }
                }
            }
        }
    }


    private fun inputLayoutBoxEnable() {

        bn.textInputLayout.boxStrokeColor =
            ContextCompat.getColor(requireContext(), R.color.main_red)
    }

    private fun inputLayoutBoxDisable() {
        bn.textInputLayout.boxStrokeColor =
            ContextCompat.getColor(requireContext(), R.color.main_dark)
    }

    @SuppressLint("SetTextI18n")
    private fun phoneEditTextManager() {
        bn.apply {
            phoneNumberEdt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                @SuppressLint("SetTextI18n")
                override fun afterTextChanged(p0: Editable?) {
                    inputLayoutBoxDisable()
                    if (!phoneNumberEdt.text!!.contains("+998(") ||
                        phoneNumberEdt.text!![0].toString() != "+"
                    ) {
                        phoneNumberEdt.setText("+998(")
                        editLastCursor()
                    }
                    if (phoneNumberEdt.text!!.length < 5) {
                        phoneNumberEdt.setText("+998(")
                        editLastCursor()
                    }

                    if (phoneNumberEdt.text!!.length == 18) {
                        hideKeyboard()
                    }
                }
            })

            phoneNumberEdt.onFocusChangeListener = View.OnFocusChangeListener { _, p1 ->
                if (p1) {
                    if (phoneNumberEdt.text!!.isEmpty())
                        phoneNumberEdt.setText("${phoneNumberEdt.text}+998(")
                    editLastCursor()
                }
            }

            phoneNumberEdt.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {

                    if (phoneNumberEdt.text!![phoneNumberEdt.text!!.length - 1].toString() == " ") {
                        phoneNumberEdt.setText(
                            phoneNumberEdt.text!!.substring(
                                0,
                                phoneNumberEdt.text!!.length - 1
                            )
                        )
                        editLastCursor()
                    } else if (phoneNumberEdt.text?.get(phoneNumberEdt.text?.length!! - 1)
                            .toString() == ")"
                    ) {
                        phoneNumberEdt.setText(
                            phoneNumberEdt.text!!.substring(
                                0,
                                phoneNumberEdt.text!!.length - 1
                            )
                        )
                        editLastCursor()
                    }

                } else {
                    if (phoneNumberEdt.text!!.length == 7) {
                        phoneNumberEdt.setText("${phoneNumberEdt.text})")
                        editLastCursor()
                    }

                    if (phoneNumberEdt.text!!.length == 8) {
                        phoneNumberEdt.setText("${phoneNumberEdt.text} ")
                        editLastCursor()
                    }
                    if (phoneNumberEdt.text!!.length == 12) {
                        phoneNumberEdt.setText("${phoneNumberEdt.text} ")
                        editLastCursor()
                    }
                    if (phoneNumberEdt.text!!.length == 15) {
                        phoneNumberEdt.setText("${phoneNumberEdt.text} ")
                        editLastCursor()
                    }
                }
                false
            }
        }
    }



    override fun onStart() {
        super.onStart()
        inputLayoutBoxDisable()
    }

    private fun editLastCursor() {
        bn.apply {
            phoneNumberEdt.setSelection(phoneNumberEdt.length())
        }
    }


}