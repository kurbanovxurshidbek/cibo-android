package com.cibo.cibo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cibo.cibo.R
import com.cibo.cibo.activity.MainActivity
import com.cibo.cibo.fragment.ProductAboutFragment
import com.cibo.cibo.fragment.RestaurantFragment
import com.cibo.cibo.fragment.RestaurantFragmentDirections
import com.cibo.cibo.model.Category
import com.cibo.cibo.model.Item

class ItemsAdapter(private val context: RestaurantFragment, private val items: List<Item>) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {

            val productAbout: DialogFragment = ProductAboutFragment.newInstance(item)

            view.findViewById<CardView>(R.id.card_parent).setOnClickListener {
                if (!productAbout.isVisible)
                    productAbout.show(context.parentFragmentManager, "productAbout")
            }
            view.findViewById<TextView>(R.id.tv_food_name).text = item.content
            Glide.with(context).load(item.img).into(view.findViewById(R.id.iv_food_photo))
        }
    }

}