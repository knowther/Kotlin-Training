package com.becas.ntt.pagingexample.retrofit

import com.becas.ntt.pagingexample.models.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): QuoteList
}