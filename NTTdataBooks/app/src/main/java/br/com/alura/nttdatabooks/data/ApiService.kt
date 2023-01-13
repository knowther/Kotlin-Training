package br.com.alura.nttdatabooks.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object ApiService {
    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service = initRetrofit().create(NYTServices::class.java)
}