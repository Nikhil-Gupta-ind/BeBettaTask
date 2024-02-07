package com.example.bebettatask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bebettatask.databinding.ItemFilter2Binding
import com.example.bebettatask.databinding.ItemFilterBinding
import com.example.bebettatask.databinding.ItemFilterSpecialBinding

class FeedFilterAdapter: RecyclerView.Adapter<ViewHolder>() {

    inner class FilterVH1(val binding: ItemFilterBinding): ViewHolder(binding.root)
    inner class FilterVH2(val binding: ItemFilter2Binding): ViewHolder(binding.root)
    inner class FilterSpecialVH(val binding: ItemFilterSpecialBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> FilterVH1(ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> FilterVH2(ItemFilter2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> FilterSpecialVH(ItemFilterSpecialBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 3

    override fun getItemViewType(position: Int): Int {
        return position
    }
}