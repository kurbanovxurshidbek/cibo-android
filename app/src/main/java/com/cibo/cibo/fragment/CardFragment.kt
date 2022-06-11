package com.cibo.cibo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibo.cibo.R
import com.cibo.cibo.activity.LoginActivity
import com.cibo.cibo.adapter.CardAdapter
import com.cibo.cibo.databinding.FragmentCardBinding
import com.cibo.cibo.model.Card
import com.cibo.cibo.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CardFragment : BaseFragment() {

    companion object {
        const val REQUEST_KEY_TRASH = "productsDelete"
    }

    private var _bn: FragmentCardBinding? = null
    private val bn get() = _bn!!

    private val productList = ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = it.getString("productList")
            val type: Type = object : TypeToken<ArrayList<Card>>() {}.type
            productList.clear()
            productList.addAll(Gson().fromJson(args, type))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bn = FragmentCardBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = CardAdapter()
        adapter.submitList(productList)
        adapter.submitFragment(this)
        bn.rvCart.adapter = adapter

        bn.ivBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        bn.btnOrder.setOnClickListener {
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

        bn.ivTrash.setOnClickListener {
            clearCart()
        }
    }

    fun clearCart() {
        setFragmentResult(REQUEST_KEY_TRASH, Bundle())
        findNavController().navigate(R.id.action_cardFragment_to_restaurantFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

}