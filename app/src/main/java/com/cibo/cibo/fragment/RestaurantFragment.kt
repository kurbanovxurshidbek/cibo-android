package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.adapter.CategoriesAdapter
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Item
import com.google.android.material.tabs.TabLayout

class RestaurantFragment : BaseFragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {

        Glide.with(requireContext())
            .load("https://images.unsplash.com/photo-1513639776629-7b61b0ac49cb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1167")
            .into(view.findViewById(R.id.detailImageView))

        tabLayout = view.findViewById(R.id.tabLayout)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }

        initTabLayout()
        initRecycler()
        initMediator()

    }

    private fun initTabLayout() {
        for (category in categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category.name))
        }
    }

    private fun initRecycler() {
        recyclerView.adapter = CategoriesAdapter(requireContext(), categories)
    }

    private fun initMediator() {
        TabbedListMediator(
            recyclerView,
            tabLayout,
            categories.indices.toList()
        ).attach()
    }

    private val categories = mutableListOf(
        Category(
            "Burgers",
            Item("Big Burger","https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465"),
            Item("Medium Burger","https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171"),
            Item("Small Burger","https://images.unsplash.com/photo-1626082892105-1650809e18aa?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170"),
        ),
        Category(
            "Pizza",
            Item("Pizza Small","https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388"),
            Item("Pizza Medium","https://images.unsplash.com/photo-1590947132387-155cc02f3212?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170"),
            Item("Pizza Max","https://images.unsplash.com/photo-1571407970349-bc81e7e96d47?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025"),
        ),
        Category(
            "Drinks",
            Item("Cola","https://images.unsplash.com/photo-1624552184280-9e9631bbeee9?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"),
            Item("Pepsi","https://images.unsplash.com/photo-1629186235045-80d4147d90dc?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464"),
            Item("Fanta","https://images.unsplash.com/photo-1598419161288-9f2f26c85590?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1200"),
            Item("Sprite","https://images.unsplash.com/photo-1625772299848-391b6a87d7b3?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"),
        ),
        Category(
            "Burgers",
            Item("Big Burger","https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465"),
            Item("Medium Burger","https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171"),
            Item("Small Burger","https://images.unsplash.com/photo-1626082892105-1650809e18aa?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170"),
        ),
        Category(
            "Pizza",
            Item("Pizza Small","https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388"),
            Item("Pizza Medium","https://images.unsplash.com/photo-1590947132387-155cc02f3212?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170"),
            Item("Pizza Max","https://images.unsplash.com/photo-1571407970349-bc81e7e96d47?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025"),
        ),
        Category(
            "Drinks",
            Item("Cola","https://images.unsplash.com/photo-1624552184280-9e9631bbeee9?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"),
            Item("Pepsi","https://images.unsplash.com/photo-1629186235045-80d4147d90dc?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464"),
            Item("Fanta","https://images.unsplash.com/photo-1598419161288-9f2f26c85590?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1200"),
            Item("Sprite","https://images.unsplash.com/photo-1625772299848-391b6a87d7b3?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387"),
        )
    )



}