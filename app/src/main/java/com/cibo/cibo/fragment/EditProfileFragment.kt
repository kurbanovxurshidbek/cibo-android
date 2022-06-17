package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.cibo.cibo.R
import com.cibo.cibo.databinding.FragmentEditProfileBinding
import com.cibo.cibo.manager.PrefsManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class EditProfileFragment : BaseFragment() {

    private var _bn: FragmentEditProfileBinding? = null
    private val bn get() = _bn!!

    lateinit var et_name: TextInputEditText
    lateinit var et_number: TextInputEditText
    lateinit var et_surname: TextInputEditText
    lateinit var et_birthday: TextInputEditText

    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setTransparentStatusBarColor(requireContext(),
            R.color.black,
            R.color.white,
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
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
        bn.textInputBirthDate.setText(PrefsManager.getInstance(requireContext())!!.getData("b_day"))



        bn.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        bn.btnDone.setOnClickListener {
            val newName = bn.tvFullname.text.toString()
            val newPhoneNumber = bn.tvPhoneNumber.text.toString()
            val birthday = bn.textInputBirthDate.text.toString()

            if (newPhoneNumber.length == 18 && newName.isNotEmpty()) {
                val args = Bundle()
                args.putString("name", newName)
                args.putString("number", newPhoneNumber)
                args.putString("b_day", birthday)
                setFragmentResult("user", args)

                findNavController().navigateUp()
            } else {

                if (newPhoneNumber.length < 18) {
                    errorCase("Invalid number", bn.textInputLayoutNumber)
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

            override fun afterTextChanged(p0: Editable?) {}
        })

        et_number.setOnFocusChangeListener { View, hasFocos ->
            if (hasFocos) {
                if (et_number.text!!.isEmpty()) {
                    et_number.setText("${et_number.text}+998(")
                    editLastCursor()
                }

            } else {
                if (et_number.text!!.toString() == "+998(")
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



        et_birthday.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (et_birthday.text!!.isEmpty()) {
                    showSelectDateDialog()
                    hideKeyboard()
                }
            }

        })

        et_birthday.setOnFocusChangeListener { View, hasFocos ->
            if (hasFocos) {
                showSelectDateDialog()
            } else {

            }
        }

        dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val date = "$day/${month.plus(1)}/$year"
            et_birthday.setText(date)
            PrefsManager.getInstance(requireContext())!!
                .saveData("b_day", et_birthday.text!!.toString())
            hideKeyboard()
            editLastCursor()
        }

    }

    private fun showSelectDateDialog() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)



        val dialog = DatePickerDialog(requireContext(),
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            dateSetListener, year, month, day)
        dialog.show()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.BottomsheetDialogAnim
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.datePicker.maxDate = System.currentTimeMillis()

        dialog.setOnCancelListener {
            et_birthday.setText(PrefsManager.getInstance(requireContext())!!.getData("b_day"))
            editLastCursor()
        }

    }

    private fun editLastCursor() {
        bn.textInputNumber.setSelection(bn.textInputNumber.length())
        bn.textInputBirthDate.setSelection(bn.textInputBirthDate.length())
        bn.textInputName.setSelection(bn.textInputName.length())
        bn.textInputSurName.setSelection(bn.textInputSurName.length())
        bn.textInputGender.setSelection(bn.textInputGender.length())
    }

    private fun errorCase(msg: String, id: TextInputLayout) {
        toaster(msg)
        val vibe: Vibrator =
            activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibe.vibrate(150)
        id.boxStrokeColor =
            ContextCompat.getColor(requireContext(), R.color.main_red)
    }

}

