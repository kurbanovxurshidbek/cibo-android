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
import com.cibo.cibo.databinding.FragmentProfileBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ProfileFragment : BaseFragment() {
    private var _bn: FragmentProfileBinding? = null
    private val bn get() = _bn!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bn = FragmentProfileBinding.inflate(inflater, container, false)
        return bn.root
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onResultReceived(msg: String){
        bn.tvFullname.text = msg
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {


        val ll_language: LinearLayout = bn.llLanguage
        ll_language.setOnClickListener {
            showChooseLanguage()
        }

        val ll_history: LinearLayout = bn.llHistory
        ll_history.setOnClickListener {

        }

        val ll_change_settings: LinearLayout = bn.llChangeSettings
        ll_change_settings.setOnClickListener {
            openChangeSettingsActivity()
//            openChangeSettings()
        }

    }

    private fun openChangeSettingsActivity(){

    }
/*    private fun openChangeSettings() {
        requireFragmentManager().beginTransaction().replace(R.id.container, ChangeSettingsFragment())
            .commit()
    }*/

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