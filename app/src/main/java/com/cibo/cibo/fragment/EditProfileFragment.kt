package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentChangeSettingsBinding
import org.greenrobot.eventbus.EventBus

class EditProfileFragment : BaseFragment() {
    private var _bn: FragmentChangeSettingsBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.getDecorView()
            .setSystemUiVisibility(ContextCompat.getColor(requireContext(), R.color.black)) //  set status text dark
       requireActivity().window.setStatusBarColor(ContextCompat.getColor(requireContext(),
            R.color.intro_text_color)) // set status bar color
        _bn = FragmentChangeSettingsBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        val btn_back = bn.btnBack
        val btn_done = bn.btnDone
        val et_name = bn.etName
        val et_number = bn.etNumber

        btn_back.setOnClickListener {

        }

        btn_done.setOnClickListener {
            val msg = et_name.text
            if (msg.isNotEmpty()){
                EventBus.getDefault().post(msg)
            }

        }
      }
    }

    private fun initView() {




}