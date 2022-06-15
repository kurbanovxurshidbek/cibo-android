package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.databinding.ItemHistoryBinding
import com.cibo.cibo.model.ItemHistory


class HistoryItemsAdapter() : RecyclerView.Adapter<HistoryItemsAdapter.VH>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class VH(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val details = dif.currentList[adapterPosition]
            binding.apply {
            /*    tvHisotryName.text = details.order_name
                tvHistoryDate.text=details.date
                tvHistoryPrice.text=details.price
                tvHistoryType.text=details.pay_type
                tvOrderNum.text=details.order_number
                tvHistoryLocation.text=details.location*/

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    fun submitList(list: List<ItemHistory>) {
        dif.submitList(list)
    }

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<ItemHistory>() {
            override fun areItemsTheSame(oldItem: ItemHistory, newItem: ItemHistory): Boolean =
                oldItem.order_name == newItem.order_name &&    oldItem.date == newItem.date &&    oldItem.order_name == newItem.order_name &&    oldItem.price == newItem.price && oldItem.location == newItem.location

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ItemHistory, newItem: ItemHistory): Boolean =
                oldItem == newItem
        }
    }
}