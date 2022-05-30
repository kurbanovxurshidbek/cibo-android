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
import com.cibo.cibo.model.Item

class ItemsAdapter(private val context: Context, private val items: List<Item>) :
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
            val args = Bundle()
            args.putSerializable("productAbout", item)
            view.findViewById<CardView>(R.id.card_parent).setOnClickListener {
                val navController = it.findNavController()
                if (navController.currentDestination?.id != R.id.productAboutFragment)
                    navController.navigate(R.id.action_restaurantFragment_to_productAboutFragment, args)
            }
            view.findViewById<TextView>(R.id.tv_food_name).text = item.content
            Glide.with(context).load(item.img).into(view.findViewById(R.id.iv_food_photo))
        }
    }

}