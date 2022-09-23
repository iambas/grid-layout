package com.suphakrit.gridapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suphakrit.gridapplication.R
import com.suphakrit.gridapplication.SUB_CATEGORY_SPAN
import com.suphakrit.gridapplication.databinding.ItemMenuBinding
import com.suphakrit.gridapplication.databinding.ItemMenuDetailBinding

class MenuAdapter constructor(
    private val onMainItemClicked: (MenuModel) -> Unit,
    private val onSubMenuClicked: (MenuModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<MenuModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MenuType.DETAIL.ordinal -> {
                val binding = ItemMenuDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MenuDetailViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MenuViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MenuViewHolder -> {
                holder.bind(items[position])
            }
            is MenuDetailViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<MenuModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: MenuModel) {
            binding.nameTextView.setOnClickListener {
                onMainItemClicked.invoke(menu)
            }
            binding.nameTextView.text = menu.name
            val color = if (menu.isSelected) R.color.teal_700 else R.color.white
            binding.nameTextView.setBackgroundResource(color)
        }
    }

    inner class MenuDetailViewHolder(
        private val binding: ItemMenuDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var menu: MenuModel? = null
        private var menuDetails = listOf<MenuDetailModel>()
        private val menuAdapter = MenuDetailAdapter(
            onItemClicked = { onItemClicked(it) },
        )

        fun bind(menuItem: MenuModel) {
            menu = menuItem
            menuDetails = menuItem.menuDetails
            binding.menuDetailRecyclerView.apply {
                layoutManager = GridLayoutManager(context, SUB_CATEGORY_SPAN)
                adapter = menuAdapter
            }
            menuAdapter.update(menuDetails)
        }

        private fun onItemClicked(menuItem: MenuDetailModel) {
            menuDetails.map {
                if (it.id == menuItem.id) {
                    it.isSelected = !it.isSelected
                }
                it
            }
            menuAdapter.update(menuDetails)

            menu?.let(onSubMenuClicked)
        }
    }
}
