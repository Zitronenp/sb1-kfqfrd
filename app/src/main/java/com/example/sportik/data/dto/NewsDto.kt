package com.example.sportik.data.dto

data class ListNewsDto(
    val news: List<NewsDto>
)

data class NewsDto(
    val id: Int,
    val title: String,
    val date: String,
    val image: String?
)

data class NewsWithContentDto(
    val news: NewsDetailDto
)

data class NewsDetailDto(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val image: String?
)