package com.example.bebettatask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.databinding.ItemBannerBinding
import com.example.bebettatask.databinding.ItemLiveScoresBinding
import com.example.bebettatask.databinding.ItemMatchPreviewListBinding
import com.example.bebettatask.databinding.ItemMostHappeningBinding
import com.example.bebettatask.databinding.ItemRapidFireBinding

class HomeFeedAdapter: RecyclerView.Adapter<ViewHolder>() {

    private val list: MutableList<HomeFeed.Data> = mutableListOf()

    inner class LiveScoresVH(val binding: ItemLiveScoresBinding): ViewHolder(binding.root)
    inner class MostHappeningVH(val binding: ItemMostHappeningBinding): ViewHolder(binding.root)
    inner class BannerVH(val binding: ItemBannerBinding): ViewHolder(binding.root)
    inner class RapidFireVH(val binding: ItemRapidFireBinding): ViewHolder(binding.root)
    inner class MatchesPreviewVH(val binding: ItemMatchPreviewListBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> LiveScoresVH(ItemLiveScoresBinding.inflate(inflater, parent, false))
            1 -> MostHappeningVH(ItemMostHappeningBinding.inflate(inflater, parent, false))
            2 -> BannerVH(ItemBannerBinding.inflate(inflater, parent, false))
            3 -> RapidFireVH(ItemRapidFireBinding.inflate(inflater, parent, false))
            4 -> MatchesPreviewVH(ItemMatchPreviewListBinding.inflate(inflater, parent, false))
            else -> object : RecyclerView.ViewHolder(View(parent.context)) {}
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is LiveScoresVH -> {
                holder.binding.apply {
                    rvMatches.adapter = LiveScoresAdapter(item.scores)
                    rvMatches.setHasFixedSize(true)
                }
            }
            is MostHappeningVH -> {
                holder.binding.rvTickr.apply {
                    adapter = MostHappeningAdapter(item.tickr)
                    setHasFixedSize(true)
                }
            }
            is BannerVH -> {
//                item.image
//                holder.binding.imageView can be changed
            }
            is RapidFireVH -> {
                holder.binding.apply {
                    tvTitle.text = item.title
                    tvCount.text = item.count
                    // questions can be updated
//                item.questions
                }
            }
            is MatchesPreviewVH -> {
                holder.binding.rvVideos.apply {
                    adapter = VideosAdapter(item.videos)
                    setHasFixedSize(true)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            "live_scores" -> 0
            "most_happening" -> 1
            "banner" -> 2
            "rapid_fire" -> 3
            "matches_preview" -> 4
            else -> 5 // data changes at back
        }
    }

    fun updateList(feed: HomeFeed) {
        list.clear()
        list.addAll(feed.data)
        notifyDataSetChanged()
    }
}