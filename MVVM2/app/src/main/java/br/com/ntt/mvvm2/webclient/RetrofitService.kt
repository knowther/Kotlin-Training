package br.com.ntt.mvvm2.webclient



import br.com.ntt.mvvm2.webclient.services.LiveService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
//
//    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    val okHttp= OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val liveService = retrofit.create(LiveService::class.java)

    }