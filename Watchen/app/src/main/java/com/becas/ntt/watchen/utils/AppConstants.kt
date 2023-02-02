package com.becas.ntt.watchen.utils

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

object AppConstants {

    const val TMDB_API_KEY = "48e494ca4aad19dae89d740976eb5dc7"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w200/"
    const val POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val MOVIE_ID = "MOVIE_ID"

}