package com.example.bebettatask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.databinding.ItemMatchPreviewBinding

class VideosAdapter(
    val list: List<HomeFeed.Data.Video>
): RecyclerView.Adapter<ViewHolder>() {

    inner class VideosVH(val binding: ItemMatchPreviewBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return VideosVH(ItemMatchPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as VideosVH).binding.apply {
//            list[position].thumbnail
            tvDuration.text = list[position].duration
        }
    }

    override fun getItemCount(): Int = list.size
}