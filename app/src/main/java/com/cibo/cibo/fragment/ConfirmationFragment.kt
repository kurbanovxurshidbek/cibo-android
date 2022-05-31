package com.cibo.cibo.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentConfirmationBinding
import com.cibo.cibo.utils.TextWatcherWrapper
import kotlinx.coroutines.*


class ConfirmationFragment : BaseFragment() {

    private var _bn: FragmentConfirmationBinding? = null
    private val bn get() = _bn!!

    private var sec = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentConfirmationBinding.inflate(inflater, container, false)
        return bn.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showKeyboard(bn.ed1)
        perSecond()
        phoneNumberColor()
        inputSmsCodeManager()
        resendTextClickManager()
    }




    private fun resendTextClickManager() {
        bn.apply {
            tvResend.setOnClickListener {
                if (tvResend.text == getString(R.string.resend)) {
                    toaster("Resend")
//                    callRequestCodeToServer()
                    perSecond()
                }
            }
        }
    }




    private fun inputSmsCodeManager() {
        bn.apply {
            //edit txt 1
            ed1.addTextChangedListener(textWatcherET1)
            ed1.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (ed1.text.isEmpty()) {
                        ed1.setBackgroundResource(R.drawable.edtextbackground)
                    }
                }
                false
            }
            //edit txt 2
            ed2.addTextChangedListener(textWatcherET2)
            ed2.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (ed2.text.isEmpty()) {
                        ed2.setBackgroundResource(R.drawable.edtextbackground)
                        ed1.requestFocus()
                    }
                }
                false
            }
            //edit txt 3
            ed3.addTextChangedListener(textWatcherET3)
            ed3.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (ed3.text.isEmpty()) {
                        ed3.setBackgroundResource(R.drawable.edtextbackground)
                        ed2.requestFocus()
                    }
                }
                false
            }
            //edit txt 4
            ed4.addTextChangedListener(textWatcherET4)
            ed4.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (ed4.text.isEmpty()) {
                        Log.d("@@@", "4 DEL")
                        ed4.setBackgroundResource(R.drawable.edtextbackground)
                        allEditTextClickableTrue()
                        ed3.requestFocus()
                    }
                }
                false
            }
        }
    }

    private val textWatcherET1 = object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            if (s.length == 1) {
                bn.ed2.requestFocus()
                bn.ed1.setBackgroundResource(R.drawable.code_background)
                checkAllEditToSendCodeServer()
            }
        }
    }
    private val textWatcherET2 = object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            if (s.length == 1) {
                bn.ed3.requestFocus()
                bn.ed2.setBackgroundResource(R.drawable.code_background)
                checkAllEditToSendCodeServer()
            }
        }
    }
    private val textWatcherET3 = object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            if (s.length == 1) {
                bn.ed4.requestFocus()
                bn.ed3.setBackgroundResource(R.drawable.code_background)
                checkAllEditToSendCodeServer()
            }
        }
    }
    private val textWatcherET4 = object : TextWatcherWrapper() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            if (s.length == 1) {
                bn.apply {
                    ed4.setBackgroundResource(R.drawable.code_background)
                    if (checkAllEditToSendCodeServer()) {
                        hideKeyboard()
                        allEditClearFocus()
                        allEditTextClickableFalse()
                        checkRequestServerCode(ed1.text.toString() + ed2.text.toString() + ed3.text.toString() + ed4.text.toString())
                        findNavController().navigate(R.id.action_confirmationFragment_to_registrationFragment)
                    }
                }
            }
        }
    }


    private fun checkRequestServerCode(code: String) {
        Toast.makeText(context, "Server Send: $code", Toast.LENGTH_SHORT).show()
    }
    private fun checkAllEditToSendCodeServer(): Boolean {
        bn.apply {
            if (ed1.text.isNotEmpty() && ed2.text.isNotEmpty() && ed3.text.isNotEmpty() && ed4.text.isNotEmpty()) {
                return true
            }
        }
        return false
    }



    private fun allEditTextClickableFalse() {
        bn.apply {
            ed1.isEnabled = false
            ed2.isEnabled = false
            ed3.isEnabled = false
            ed4.isEnabled = false
        }
    }


    private fun allEditTextClickableTrue() {
        bn.apply {
            ed1.isClickable = true
            ed2.isClickable = true
            ed3.isClickable = true
            ed4.isClickable = true
        }
    }

    private fun allEditClearFocus() {
        bn.apply {
            ed1.clearFocus()
            ed2.clearFocus()
            ed3.clearFocus()
            ed4.clearFocus()
        }
    }

    private fun phoneNumberColor() {
        bn.tvFourDigit.text = Html.fromHtml(
            "Please enter the 4 diget security code we just sent you at " + "<font color=${
                ContextCompat.getColor(
                    requireContext(),
                    R.color.main_yellow
                )
            }>" + arguments?.getString(
                "phoneNumber"
            )
        )
    }


    private fun perSecond(): Job {
        if (sec == 0) sec = 10
        return MainScope().launch {
            while (isActive) {
                sec--
                val min = sec / 60
                var s = sec - min * 60
//                if (s < 10)
//                    binding.tvResend.text = "Resend in $min:0$s Sec"
//                else
                bn.tvResend.text = "Resend In $min:$s Sec"
                if (sec == 0) {
                    bn.tvResend.text = getString(R.string.resend)
                    bn.tvResend.setTextColor(Color.parseColor("#ff02c65c"))
                    cancel()
                }
                delay(1000)
            }
        }
    }



  }