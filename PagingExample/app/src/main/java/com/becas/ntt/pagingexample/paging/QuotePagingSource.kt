package com.becas.ntt.pagingexample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.becas.ntt.pagingexample.models.Result
import com.becas.ntt.pagingexample.retrofit.QuoteAPI

class QuotePagingSource(val quoteAPI: QuoteAPI): PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

//        if(state.anchorPosition !== null){
//            val anchor = state.closestPageToPosition(state.anchorPosition!!)
//            if (anchor?.prevKey != null){
//                return anchor.prevKey!!.plus(1)
//            }
//            else if(anchor?.nextKey !== null){
//                return  anchor.nextKey!!.minus(1)
//            }
//        }else{
//            return null
//        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if(position == 1) null else position -1,
                nextKey = if(position == response.totalPages) null else position + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}