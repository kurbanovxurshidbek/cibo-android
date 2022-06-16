package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.databinding.ItemFoodBinding
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.model.Food
import com.cibo.cibo.utils.Constants.LOAD_ATTACH_URL

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
            args.putBoolean("isAbout", false)

            binding.apply {
                tvFoodName.text = food.name

                Glide.with(context).load(LOAD_ATTACH_URL + food.attachId)
                    .placeholder(R.color.main_silver_light)
                    .into(ivFoodPhoto)

                tvFoodPrice.text = food.price.toString().plus(" so'm")

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
                oldItem.name == newItem.name

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem == newItem
        }
    }

}