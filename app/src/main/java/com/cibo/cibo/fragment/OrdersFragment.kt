package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.cibo.cibo.adapter.OrdersViewPagerAdapter
import com.cibo.cibo.databinding.FragmentEditProfileBinding
import com.cibo.cibo.databinding.FragmentOrdersBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class OrdersFragment: Fragment() {

    lateinit var adapter:OrdersViewPagerAdapter

    private lateinit var _bn: FragmentOrdersBinding
    private val bn get() = _bn



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bn = FragmentOrdersBinding.inflate(inflater, container, false)
        return bn.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }



    private fun initViews() {

        adapter = OrdersViewPagerAdapter(childFragmentManager,lifecycle)
        bn.vpOrders.adapter = adapter

        bn.tlOrders.addTab( bn.tlOrders.newTab().setText("Active Orders"))
        bn.tlOrders.addTab( bn.tlOrders.newTab().setText("History"))

        bn.tlOrders.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                bn.vpOrders.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {
            }


        })

        bn.vpOrders.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bn.tlOrders.selectTab( bn.tlOrders.getTabAt(position))
            }
        })

    }
}