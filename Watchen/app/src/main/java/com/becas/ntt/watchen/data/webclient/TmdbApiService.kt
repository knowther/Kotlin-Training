package com.becas.ntt.watchen.data.webclient

import com.becas.ntt.watchen.data.webclient.model.dto.MovieFindedDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("trending/movie/day")
    fun getTrending(
    ): Call<MovieResponseDTO>

   @GET("discover/movie")
    fun getDiscover(
       @Query("language") language: String,
   ): Call<MovieResponseDTO>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
        @Query("append_to_response") videos: String = "videos"
    ): Call<MovieFindedDTO>

    @GET("movie/upcoming")
    fun getUpcoming(): Call<MovieResponseDTO>

    @GET("account")
    fun getUser()

}