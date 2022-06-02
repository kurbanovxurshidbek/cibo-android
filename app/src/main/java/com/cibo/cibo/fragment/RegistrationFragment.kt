package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cibo.cibo.R
import com.cibo.cibo.activity.MainActivity
import com.cibo.cibo.databinding.FragmentLoginBinding
import com.cibo.cibo.databinding.FragmentRegistrationBinding


class RegistrationFragment : BaseFragment() {


    private var _bn: FragmentRegistrationBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentRegistrationBinding.inflate(inflater, container, false)
        return bn.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showKeyboard(bn.nameEdt)
        sensorTohideKeyBoard()
        openToHomeFragment()

    }

    @SuppressLint("SetTextI18n")
    private fun openToHomeFragment() {
        bn.signUpBtn.apply {
            setOnClickListener {
                text = "Loading..."
                Intent(requireActivity(), MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    private fun sensorTohideKeyBoard() {
        bn.linearLayout.setOnClickListener {
            hideKeyboard()
        }
    }
}