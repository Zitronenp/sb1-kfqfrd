package com.example.sportik.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportik.data.dto.NewsDto
import com.example.sportik.databinding.ItemNewsBinding

class NewsAdapter : PagingDataAdapter<NewsDto, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class NewsViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: NewsDto) {
            binding.apply {
                titleTextView.text = news.title
                dateTextView.text = news.date
                
                news.image?.let { imageUrl ->
                    Glide.with(root)
                        .load(imageUrl)
                        .into(newsImageView)
                }
            }
        }
    }

    private class NewsDiffCallback : DiffUtil.ItemCallback<NewsDto>() {
        override fun areItemsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsDto, newItem: NewsDto): Boolean {
            return oldItem == newItem
        }
    }
}