package com.becas.ntt.pagingexample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.becas.ntt.pagingexample.paging.QuotePagingSource
import com.becas.ntt.pagingexample.retrofit.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteAPI: QuoteAPI) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {QuotePagingSource(quoteAPI)}
    ).liveData

}