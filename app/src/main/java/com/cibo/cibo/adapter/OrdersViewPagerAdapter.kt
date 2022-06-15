package com.cibo.cibo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cibo.cibo.fragment.ActiveOrderFragment
import com.cibo.cibo.fragment.HistoryFragment


class OrdersViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        if(position == 1) {
            return ActiveOrderFragment()
        }
        return HistoryFragment()

    }
    override fun getItemCount(): Int {
        return 2
    }
}