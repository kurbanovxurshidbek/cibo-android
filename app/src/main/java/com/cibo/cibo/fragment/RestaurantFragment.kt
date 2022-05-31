package com.cibo.cibo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.adapter.CategoriesAdapter
import com.cibo.cibo.databinding.FragmentRestaurantBinding
import com.cibo.cibo.helper.SpacesItemDecoration
import com.cibo.cibo.model.Card
import com.cibo.cibo.model.Food
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson


class RestaurantFragment : BaseFragment() {

    private var _bn: FragmentRestaurantBinding? = null
    private val bn get() = _bn!!

    private var isCheckedTab = false
    private var lastTab: TabLayout.Tab? = null
    private var productCount = 0
    private var productMap = HashMap<Food, Int>()
    private var productPrice = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bn = FragmentRestaurantBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()

        if (isCheckedTab) {
            bn.motionLayout.jumpToState(R.id.end)
            bn.tabLayout.selectTab(lastTab)
        }

        if (productCount != 0)
            openCartButton(0, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

    private fun initViews() {

        Glide.with(requireContext())
            .load("https://images.unsplash.com/photo-1513639776629-7b61b0ac49cb?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1167")
            .into(bn.detailImageView)

        bn.cardView.setOnClickListener {
            val args = Bundle()
            val data: String = Gson().toJson(getCardList(productMap))
            args.putString("productList", data)
            findNavController().navigate(R.id.action_restaurantFragment_to_cardFragment, args)
        }

        initTabLayout()
        initRecycler()
        initMediator()
        changeBrandNameSize()
        checkTabLayoutClicked()
    }

    private fun checkTabLayoutClicked() {
        bn.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                isCheckedTab = tab != bn.tabLayout.getTabAt(0)
                lastTab = tab
                bn.motionLayout.transitionToEnd()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun changeBrandNameSize() {
        bn.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                if (progress == 0.5f)
                    bn.tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (currentId == R.id.end)
                    bn.tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                else
                    bn.tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }

    private fun initTabLayout() {
        for (category in categories) {
            bn.tabLayout.addTab(bn.tabLayout.newTab().setText(category.name))
        }
    }

    private fun initRecycler() {
        val newAdapter = CategoriesAdapter(this)
        newAdapter.submitList(categories)
        bn.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(SpacesItemDecoration(320))
            adapter = newAdapter
        }
    }


    private fun initMediator() {
        TabbedListMediator(
            bn.recyclerView,
            bn.tabLayout,
            categories.indices.toList()
        ).attach()
    }

    @SuppressLint("SetTextI18n")
    fun openCartButton(count: Int, food: Food?) {
        productCount += count
        bn.parentMotionLayout.transitionToEnd()
        bn.productCount.text = productCount.toString()
        food?.let { it ->

            var newCount = productMap.getOrDefault(it, 0)
            newCount++
            productMap[it] = newCount

            it.price?.let {
                productPrice += it
            }
        }
        bn.cartPrice.text = productPrice.toInt().toString() + " so'm"
    }

    private fun getCardList(map: HashMap<Food, Int>): ArrayList<Card> {
        val list = ArrayList<Card>()
        map.forEach { (food, count) ->
            list.add(Card(food, count))
        }
        return list
    }

}