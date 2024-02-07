package com.example.bebettatask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.data.repository.HomeFeedRepository
import com.example.bebettatask.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeFeedRepository
) : ViewModel() {

    val feed: StateFlow<Result<HomeFeed>>
        get() = repository.feed

    fun getHomeFeed() {
        viewModelScope.launch {
            repository.getHomeFeed()
        }
    }
}