package com.cibo.cibo.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.databinding.ItemTrashBinding
import com.cibo.cibo.model.Card


class CardAdapter(itemList: List<Card>, context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private var itemListOrder: List<Card>
    private val context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ViewHolder {
        return ViewHolder(ItemTrashBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind()


    override fun getItemCount(): Int {
        return itemListOrder.size
    }

    inner class ViewHolder(private val binding:ItemTrashBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemTrash = itemListOrder[adapterPosition]
        fun bind(){
//            binding.tvProductImg =
        }
    }

    init {
        itemListOrder = itemList
        this.context = context
    }
}