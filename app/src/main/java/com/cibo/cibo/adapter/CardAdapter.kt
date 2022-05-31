package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.databinding.ItemCategoryBinding
import com.cibo.cibo.databinding.ItemTrashBinding
import com.cibo.cibo.model.Card
import com.cibo.cibo.model.Category


class CardAdapter(itemList: List<Card>, context: Context) :
    RecyclerView.Adapter<CardAdapter.VH>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class VH(private val binding: ItemTrashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val details = dif.currentList[adapterPosition]
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.VH {
        return VH(
            ItemTrashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: CardAdapter.VH, position: Int) = holder.bind()


    fun submitList(list: List<Card>) {
        dif.submitList(list)
    }

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean =
                oldItem.title == newItem.title

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean =
                oldItem == newItem
        }
    }
}