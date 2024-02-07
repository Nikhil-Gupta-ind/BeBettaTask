package com.example.bebettatask.data.repository

import android.util.Log
import com.example.bebettatask.R
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.utils.JsonHelper
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeFeedRepository @Inject constructor(
    private val jsonHelper: JsonHelper
) {

    private val _feed = MutableStateFlow<Result<HomeFeed>>(Result.Loading())
    val feed: StateFlow<Result<HomeFeed>>
        get() = _feed.asStateFlow()

    suspend fun getHomeFeed() {
        val json = withContext(Dispatchers.IO) {
            jsonHelper.getFeedFromRaw(R.raw.feeds)
        }
        if (json.isNotEmpty()) {
            try {
                val homeFeed = Gson().fromJson(json, HomeFeed::class.java)
                _feed.emit(Result.Success(homeFeed))
            } catch (e: Exception) {
                Log.e("Response", "getHomeFeed: ${e.message}", )
                _feed.emit(Result.Error(e.localizedMessage))
            }
        } else {
            _feed.emit(Result.Error("Something went wrong"))
        }
    }
}