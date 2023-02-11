package com.becas.ntt.pagingexample.di

import com.becas.ntt.pagingexample.models.QuoteList
import com.becas.ntt.pagingexample.retrofit.QuoteAPI
import com.becas.ntt.pagingexample.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getQuoteAPI(retrofit: Retrofit): QuoteAPI{
        return retrofit.create(QuoteAPI::class.java)
    }

}