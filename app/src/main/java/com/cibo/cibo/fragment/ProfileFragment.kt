package com.cibo.cibo.fragment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.cibo.cibo.R
import com.cibo.cibo.databinding.BottomSheetBinding
import com.cibo.cibo.databinding.FragmentProfileBinding
import com.cibo.cibo.manager.PrefsManager

class ProfileFragment : BaseFragment() {

    private var _bn: FragmentProfileBinding? = null
    private val bn get() = _bn!!

    private var _dBn: BottomSheetBinding? = null
    private val dBn get() = _dBn!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _bn = FragmentProfileBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
        _dBn = null
    }

    private fun initView() {
        loadData()

        setFragmentResultListener("user"){_, bundle ->
            bn.tvFullname.text = bundle.getString("name")
            bn.tvPhoneNumber.text = bundle.getString("number")
            var birthday = bundle.getString("b_day")
            saveAboutUser(bn.tvFullname.text.toString(), bn.tvPhoneNumber.text.toString(), birthday.toString())
        }


        val dialog = getBottomSheet()

        bn.llLanguage.setOnClickListener {
            showChooseLanguage(dialog)
        }

        bn.llChangeSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)

        }
        bn.llAboutUs.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutUsFragment)

        }

        bn.llNotification.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_notificationFragment)
        }
    }

    fun saveAboutUser(name: String, number: String, birthday: String){
        PrefsManager.getInstance(requireContext())!!.saveData("name", name)
        PrefsManager.getInstance(requireContext())!!.saveData("number", number)
        PrefsManager.getInstance(requireContext())!!.saveData("b_day", birthday)
    }

    fun loadData(){
        bn.tvFullname.text = PrefsManager.getInstance(requireContext())!!.getData("name")
        bn.tvPhoneNumber.text = PrefsManager.getInstance(requireContext())!!.getData("number")
    }

    private fun getBottomSheet(): Dialog {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        _dBn = BottomSheetBinding.inflate(dialog.layoutInflater)
        dialog.setContentView(dBn.root)
        return dialog

    }

    private fun showChooseLanguage(dialog: Dialog) {

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.BottomsheetDialogAnim
        dialog.window!!.setGravity(Gravity.BOTTOM)

        dBn.llUz.setOnClickListener {
            Toast.makeText(requireContext(), "uzb", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dBn.llRus.setOnClickListener {
            Toast.makeText(requireContext(), "ru", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dBn.llEng.setOnClickListener {
            Toast.makeText(requireContext(), "eng", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

}


