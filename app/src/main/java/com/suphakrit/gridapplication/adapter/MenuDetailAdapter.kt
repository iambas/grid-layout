package com.suphakrit.gridapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suphakrit.gridapplication.R
import com.suphakrit.gridapplication.databinding.ItemMenuDetailItemBinding

class MenuDetailAdapter constructor(
    private val onItemClicked: (MenuDetailModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<MenuDetailModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMenuDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder((binding))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.count()

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<MenuDetailModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(
        private val binding: ItemMenuDetailItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: MenuDetailModel) {
            binding.nameTextView.setOnClickListener {
                onItemClicked.invoke(menu)
            }
            binding.nameTextView.text = menu.name
            val color = if (menu.isSelected) R.color.teal_700 else R.color.white
            binding.nameTextView.setBackgroundResource(color)
        }
    }
}
