package com.example.bebettatask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.databinding.ItemLiveScoresBinding
import com.example.bebettatask.databinding.ItemScoreBinding

class LiveScoresAdapter(
    private val list: List<HomeFeed.Data.Score>
): RecyclerView.Adapter<ViewHolder>() {

    inner class LiveScoreVH(val binding: ItemScoreBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LiveScoreVH(ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        (holder as LiveScoreVH).binding.apply {
            // coil
//            ivFlag1.load(item.flag1)
//            ivFlag2.load(item.flag2)
            tvCountry1.text = item.name1
            tvCountry2.text = item.name2
            tvScore1.text = item.score1.toString()
            tvScore2.text = item.score2.toString()
            tvTime.text = item.time
        }
    }

    override fun getItemCount(): Int = list.size
}