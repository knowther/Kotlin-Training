package com.becas.ntt.watchen.data.webclient

import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("trending/movie/day")
   suspend fun getTrending(
//        @Query("language") language: String,
//        @Query("page") page: Int
    ): MovieResponseDTO

   @GET("discover/movie")
    fun getDiscover(
       @Query("language") language: String,
   ): Call<MovieResponseDTO>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Query("language") language: String,
        @Path("movie_id") movieId: String
    ): Call<MovieFindedDTO>

    @GET("account")
    fun getUser()

}