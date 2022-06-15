package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cibo.cibo.adapter.CategoriesAdapter
import com.cibo.cibo.adapter.HistoryItemsAdapter
import com.cibo.cibo.databinding.FragmentHistoryBinding
import com.cibo.cibo.helper.SpacesItemDecoration
import com.cibo.cibo.model.ItemHistory

class HistoryFragment: BaseFragment() {
    private var _bn: FragmentHistoryBinding? = null
    private val bn get() = _bn!!
private  lateinit  var historlist : ArrayList<ItemHistory>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bn = FragmentHistoryBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {



fakeHistory()


    }



 private fun fakeHistory() {


     historlist=ArrayList()
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))
     historlist.add(ItemHistory("376","6.10.2022, 23:58","Big Lavash","24000","naxt","KFC"))



        val newAdapter = HistoryItemsAdapter()
        newAdapter.submitList(historlist)
        bn.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newAdapter
        }
    }

}