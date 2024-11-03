package com.example.sportik.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.network.SportsApi
import com.example.sportik.data.paging.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: SportsApi
) {
    fun getNewsPagingFlow(): Flow<PagingData<NewsDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(api) }
        ).flow
    }

    suspend fun getNewsDetails(id: Int) = api.getNewsWithDetails(id)

    companion object {
        const val PAGE_SIZE = 10
    }
}