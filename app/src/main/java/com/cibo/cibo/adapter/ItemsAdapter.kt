package com.cibo.cibo.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.model.Food

class ItemsAdapter(private val context: RestaurantFragment, private val foods: List<Food>) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(food: Food) {
            val args = Bundle()
            args.putSerializable("productAbout", food)
            view.findViewById<CardView>(R.id.card_parent).setOnClickListener {
                val navController = it.findNavController()
                if (navController.currentDestination?.id != R.id.productAboutFragment)
                    navController.navigate(
                        R.id.action_restaurantFragment_to_productAboutFragment,
                        args
                    )
            }
            view.findViewById<TextView>(R.id.tv_food_name).text = food.content
            view.findViewById<Button>(R.id.btn_count_minus).setOnClickListener {
                context.openCartButton(1)
            }
            Glide.with(context).load(food.img).into(view.findViewById(R.id.iv_food_photo))
        }
    }

}