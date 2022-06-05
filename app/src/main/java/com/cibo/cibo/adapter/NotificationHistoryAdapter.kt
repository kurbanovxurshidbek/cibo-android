package com.cibo.cibo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.databinding.ItemNotificationBinding
import com.cibo.cibo.fragment.NotificationFragment
import com.cibo.cibo.model.NotificationHistory
import com.google.android.material.imageview.ShapeableImageView

class NotificationHistoryAdapter(
    val fragment: NotificationFragment,
    val items: ArrayList<NotificationHistory>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHistoryVH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is NotificationHistoryVH){
            val img = holder.img
            val title = holder.title
            val date = holder.date
            val time = holder.time
            val description = holder.description
            val isRead = holder.isRead

            Glide.with(fragment).load(item.img).into(img)
            title.text = item.title
            date.text = item.date
            time.text = item.time
            description.text = item.description

            if (item.isRead == true){
                isRead.visibility = View.VISIBLE
            }else{
                isRead.visibility = View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class NotificationHistoryVH(val bn: ItemNotificationBinding): RecyclerView.ViewHolder(bn.root){
        var img: ShapeableImageView
        var title: TextView
        var date: TextView
        var time: TextView
        var description: TextView
        var isRead:TextView

        init {
            img = bn.ivImg
            title = bn.tvTitle
            date = bn.tvDate
            time = bn.tvTime
            description = bn.tvDescription
            isRead =  bn.tvIsRead
        }

    }
}