package com.example.sportik.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.network.SportsApi
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val api: SportsApi
) : PagingSource<Int, NewsDto>() {

    override fun getRefreshKey(state: PagingState<Int, NewsDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsDto> {
        val position = params.key ?: 0
        
        return try {
            val response = api.getNewsPaginated(position, params.loadSize)
            val news = response.body()?.news ?: emptyList()
            
            LoadResult.Page(
                data = news,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (news.isEmpty()) null else position + params.loadSize
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}