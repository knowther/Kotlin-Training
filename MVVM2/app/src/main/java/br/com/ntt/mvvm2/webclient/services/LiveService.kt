package br.com.ntt.mvvm2.webclient.services

import br.com.ntt.mvvm2.models.Live
import retrofit2.Call
import retrofit2.http.GET

interface LiveService {
    @GET("lista-lives.json")
    fun getAllLives(): Call<List<Live>>
}