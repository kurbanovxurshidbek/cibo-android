package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.databinding.ItemCategoryBinding
import com.cibo.cibo.databinding.ItemTrashBinding
import com.cibo.cibo.model.Card
import com.cibo.cibo.model.Category


class CardAdapter : RecyclerView.Adapter<CardAdapter.VH>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class VH(private val binding: ItemTrashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val details = dif.currentList[adapterPosition]
            binding.apply {
                tvProductCount.text = details.count.toString()
                val food = details.food
                Glide.with(root.context).load(food.img).into(tvProductImg)
                tvProductName.text = food.content
                tvProductPrice.text = food.price?.toInt()?.times(details.count).toString() + " so'm"

                btnCountMinus.setOnClickListener {
                    if (tvProductCount.text.toString().toInt() == 1) {
                        removeItem(details)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.VH {
        return VH(
            ItemTrashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: CardAdapter.VH, position: Int) = holder.bind()

    fun submitList(list: ArrayList<Card>) {
        dif.submitList(list)
    }

    fun removeItem(card: Card) {
        val newList = ArrayList<Card>()
        dif.currentList.forEach {
            newList.add(it)
        }
        newList.remove(card)
        submitList(newList)
    }

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean =
                oldItem.food == newItem.food

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean =
                oldItem == newItem
        }
    }
}