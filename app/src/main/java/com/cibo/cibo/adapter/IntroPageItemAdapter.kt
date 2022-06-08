package com.cibo.cibo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.R
import com.cibo.cibo.activity.IntroPageActivity
import com.cibo.cibo.app.CiboApplication
import com.cibo.cibo.databinding.ItemIntroPageBinding
import com.cibo.cibo.model.Card
import com.cibo.cibo.model.IntroPageItem
import com.cibo.cibo.utils.Utils

class IntroPageItemAdapter(var activity: IntroPageActivity) :
    RecyclerView.Adapter<IntroPageItemAdapter.MyViewHolder>() {

    private val dif = AsyncListDiffer(this, ITEM_DIFF)

    inner class MyViewHolder(private val binding: ItemIntroPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = dif.currentList[adapterPosition]
            binding.apply {

                val params: ViewGroup.LayoutParams = cardView.layoutParams
                params.height = Utils.screenSize(activity.application).width * 2 / 3
                params.width = params.height
                cardView.layoutParams = params

                tvTitle.text = item.title
                tvDescription.text = item.description
                ivAnim.setAnimation(item.img)
            }
        }
    }

    fun submitList(list: ArrayList<IntroPageItem>) {
        dif.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemIntroPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<IntroPageItem>() {
            override fun areItemsTheSame(oldItem: IntroPageItem, newItem: IntroPageItem): Boolean =
                oldItem.title == newItem.title

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: IntroPageItem,
                newItem: IntroPageItem
            ): Boolean =
                oldItem == newItem
        }
    }

    /**
     * Set ShapeableImageView height as screen width
     */
    private fun setViewHeight(view: View) {

    }
}