package com.cibo.cibo.fragment


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import com.cibo.cibo.R

class ProfileFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {

        val ll_language: LinearLayout = view.findViewById(R.id.ll_language)
        ll_language.setOnClickListener {
            showChooseLanguage()
        }

        val ll_history: LinearLayout = view.findViewById(R.id.ll_history)
        ll_history.setOnClickListener {

        }

        val ll_change_settings: LinearLayout = view.findViewById(R.id.ll_change_settings)
        ll_change_settings.setOnClickListener {
            openChangeSettings()
        }

    }

    private fun openChangeSettings() {
        requireFragmentManager().beginTransaction().replace(R.id.container, ChangeSettingsFragment())
            .commit()
    }

    private fun showChooseLanguage() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet)

        val tv_uzb: LinearLayout = dialog.findViewById(R.id.ll_uz)
        val tv_ru: LinearLayout = dialog.findViewById(R.id.ll_rus)
        val tv_eng: LinearLayout = dialog.findViewById(R.id.ll_eng)

        tv_uzb.setOnClickListener {
            Toast.makeText(requireContext(), "uzb", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        tv_ru.setOnClickListener {
            Toast.makeText(requireContext(), "ru", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        tv_eng.setOnClickListener {
            Toast.makeText(requireContext(), "eng", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.BottomsheetDialogAnim
        dialog.window!!.setGravity(Gravity.BOTTOM)

    }

}