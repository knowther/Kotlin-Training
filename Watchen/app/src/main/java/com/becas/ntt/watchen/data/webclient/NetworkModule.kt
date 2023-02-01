package com.becas.ntt.watchen.data.webclient

import com.becas.ntt.watchen.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkModule {
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", AppConstants.TMDB_API_KEY)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    //authInterceptor: Interceptor

    fun loggingClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(providesInterceptor())
            .build()
    }

    //loggingClient: OkHttpClient
    fun providesRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(loggingClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun tmdbApi(): TmdbApiService {
        return providesRetrofitInstance().create(TmdbApiService::class.java)
    }


}