package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
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

        tabLayout = view.findViewById(R.id.tabLayout)
        recyclerView = view.findViewById(R.id.recyclerView)

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
            "Category 1",
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 3"),
            Item("Item 4"),
            Item("Item 5"),
            Item("Item 6")
        ),
        Category(
            "Category 2",
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 3"),
            Item("Item 4"),
        ),
        Category(
            "Category 3",
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 3"),
            Item("Item 4"),
            Item("Item 5"),
            Item("Item 6"),
            Item("Item 7"),
            Item("Item 8"),
        ),
        Category(
            "Category 4",
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 3"),
            Item("Item 4"),
            Item("Item 5"),
            Item("Item 6")
        ),
        Category(
            "Category 5",
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 4"),
            Item("Item 5"),
        ),
    )

}