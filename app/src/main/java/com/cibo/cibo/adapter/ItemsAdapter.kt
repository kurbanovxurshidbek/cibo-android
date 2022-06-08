package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ItemFoodBinding
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Food

class ItemsAdapter(private val context: RestaurantFragment) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    fun submitList(list: List<Food>) = dif.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    inner class ItemViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val food = dif.currentList[adapterPosition]
            val args = Bundle()
            args.putSerializable("productAbout", food)

            binding.apply {
                tvFoodName.text = food.content
                Glide.with(context).load(food.img).placeholder(R.color.main_silver_light).into(ivFoodPhoto)
                tvFoodPrice.text = food.price?.toInt().toString().plus(" so'm")

                btnCountMinus.setOnClickListener {
                    context.openCartButton(1, food)
                }

                cardParent.setOnClickListener {
                    val navController = it.findNavController()
                    if (navController.currentDestination?.id != R.id.productAboutFragment)
                        navController.navigate(
                            resId = R.id.action_restaurantFragment_to_productAboutFragment,
                            args = args
                        )
                }

            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.content == newItem.content

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem == newItem
        }
    }

}