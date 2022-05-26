package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.cibo.cibo.databinding.ItemCategoryBinding
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Item


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.VH>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)
    private var itemClickListener: ItemsAdapter.ItemClickListener? = null

    inner class VH(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val details = dif.currentList[adapterPosition]
            binding.apply {
                categoryName.text = details.name
                recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = ItemsAdapter(
                        context, details.listOfItems, object : ItemsAdapter.ItemClickListener {
                            override fun itemClick(item: Item) {
                                itemClickListener!!.itemClick(item)
                            }
                        })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()


    fun submitList(list: List<Category>, itemClickListener: ItemsAdapter.ItemClickListener) {
        this.itemClickListener = itemClickListener
        dif.submitList(list)
    }

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.name == newItem.name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem
        }
    }
}