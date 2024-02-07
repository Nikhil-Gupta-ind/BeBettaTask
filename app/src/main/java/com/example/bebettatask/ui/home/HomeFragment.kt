package com.example.bebettatask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.TEXT_DIRECTION_RTL
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bebettatask.R
import com.example.bebettatask.data.model.HomeFeed
import com.example.bebettatask.data.repository.Result
import com.example.bebettatask.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val feedAdapter by lazy {
        HomeFeedAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindHandlers()
        bindObservers()
        showBottomSheet()
        viewModel.getHomeFeed()
    }

    private fun bindHandlers() {
        binding.rvFilter.apply {
            adapter = FeedFilterAdapter()
            setHasFixedSize(true)
        }
        binding.rvHomeFeed.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = feedAdapter
            setHasFixedSize(true)
        }
    }

    private fun bindObservers() {
        lifecycleScope.launch {
            viewModel.feed.collectLatest { res ->
                binding.progressBar.isVisible = false
                when (res) {
                    is Result.Success -> {
                        res.data?.let {
                            feedAdapter.updateList(it)
                        }
                    }
                    is Result.Error -> {
                        Toast.makeText(requireContext(), "${res.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.rewards_bottom_sheet)
        bottomSheetDialog.apply {
            setCancelable(false)
        }
        bottomSheetDialog.show()
        bottomSheetDialog.findViewById<TextView>(R.id.tvExplore)?.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        // animation
        bottomSheetDialog.findViewById<TextView>(R.id.text1)?.isSelected = true
        bottomSheetDialog.findViewById<TextView>(R.id.text2)?.apply {
            isSelected = true
            marqueeRepeatLimit = -1
            textDirection = TEXT_DIRECTION_RTL
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}