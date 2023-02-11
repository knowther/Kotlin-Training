package com.becas.ntt.watchen.presentation.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.TmdbApiService

class MoviePagingSource(val tmdbApiService: TmdbApiService): PagingSource<Int, MovieDTO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        return try {
            val position = params.key ?: 1
            val response = tmdbApiService.getTrending(position).body()!!
            LoadResult.Page(
                data = response.results,
                prevKey = if(position == 1) null else position -1,
                nextKey = if(position == response?.total_pages) null else position + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}