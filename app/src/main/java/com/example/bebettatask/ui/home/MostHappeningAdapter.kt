package com.example.bebettatask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.databinding.ItemTickrBinding

class MostHappeningAdapter(
    val list: List<HomeFeed.Data.Tickr>
): RecyclerView.Adapter<MostHappeningAdapter.TickrVH>() {

    inner class TickrVH(val binding: ItemTickrBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickrVH {
        return TickrVH(ItemTickrBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TickrVH, position: Int) {
        val item = list[position]
        holder.binding.apply {
//            imageView.load(item.image)
            textView2.text = item.text
            ivBlueTick.isVisible = item.hasBlueTick
        }
    }

    override fun getItemCount(): Int = list.size
}