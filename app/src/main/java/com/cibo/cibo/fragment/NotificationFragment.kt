package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.adapter.NotificationHistoryAdapter
import com.cibo.cibo.databinding.FragmentNotificationBinding
import com.cibo.cibo.model.NotificationHistory

class NotificationFragment : BaseFragment() {

    private var _bn: FragmentNotificationBinding? = null
    private val bn get() = _bn!!

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _bn = FragmentNotificationBinding.inflate(inflater, container, false)
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
        bn.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        recyclerView = bn.rvNotification
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        refreshAdapter(notification())
    }

    private fun notification(): ArrayList<NotificationHistory> {
        val  items : ArrayList<NotificationHistory>  = ArrayList()

        items.add(NotificationHistory(
            "https://www.afisha.uz/ui/catalog/2016/07/0039781.jpeg",
            "Evos",
            "22.05.22",
            "18:30",
            "Your order is ready!",
            true))

        items.add(NotificationHistory("https://dostavkainfo.uz/wp-content/uploads/2020/04/loook.jpg",
            "Loook",
            "18.10.21",
            "20:00",
            "Your order is ready",
            false))

        items.add(NotificationHistory("https://logobank.uz:8005/media/logos_preview/preview_MW-01.png",
            "MaxWay",
            "20.01.20",
            "18:23",
            "Your order is ready!",
            false))

        return  items
    }

    private fun refreshAdapter(items: ArrayList<NotificationHistory>) {
        val adapter = NotificationHistoryAdapter(this, items)
        recyclerView.adapter = adapter
    }


}