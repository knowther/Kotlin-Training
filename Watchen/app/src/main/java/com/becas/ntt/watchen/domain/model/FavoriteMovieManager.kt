package com.becas.ntt.watchen.domain.model

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first


class FavoriteMovieManager(val context: Context, key: String) {

    var loggedInScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorite_movies", scope = loggedInScope)


        private val MOVIE_ID_KEY = stringPreferencesKey(key)
//        private val MOVIE_NAME_KEY = stringPreferencesKey("MOVIE_NAME")
//        private val MOVIE_COVER_KEY = stringPreferencesKey("MOVIE_COVER")

     suspend fun saveFavoriteMovieData(movieId: String){

        context.dataStore.edit {
            it[MOVIE_ID_KEY] = movieId
//            it[MOVIE_NAME_KEY] = movieName
//            it[MOVIE_COVER_KEY] = movieCover
        }
    }

     suspend fun readFavoriteMovieData(): String?{
        val prefs = context.dataStore.data.first()
         return prefs[MOVIE_ID_KEY]
    }
}