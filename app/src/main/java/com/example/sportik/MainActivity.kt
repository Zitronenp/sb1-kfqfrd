package com.example.sportik

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sportik.databinding.ActivityMainBinding
import com.example.sportik.ui.NewsAdapter
import com.example.sportik.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeNews()
    }

    private fun setupRecyclerView() {
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun observeNews() {
        lifecycleScope.launch {
            viewModel.newsPagingFlow.collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }
    }
}