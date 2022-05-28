package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ItemCategoryBinding
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Item


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.VH>() {
    private val dif = AsyncListDiffer(this, ITEM_DIFF)
    private var fragment: RestaurantFragment? = null

    inner class VH(private val binding: ItemCategoryBinding, var fragment: RestaurantFragment) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val details = dif.currentList[adapterPosition]
            binding.apply {
                categoryName.text = details.name
                recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = ItemsAdapter(fragment, details.listOfItems)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            fragment!!
        )
    }


    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()


    fun submitList(fragment: RestaurantFragment, list: List<Category>) {
        this.fragment = fragment
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