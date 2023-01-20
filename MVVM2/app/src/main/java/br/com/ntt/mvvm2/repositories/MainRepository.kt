package com.ocanha.mvvmrecyclerviewcomretrofitemkotlin.repositories

import android.content.ContentValues
import android.util.Log
import com.ocanha.mvvmrecyclerviewcomretrofitemkotlin.models.Live
import com.ocanha.mvvmrecyclerviewcomretrofitemkotlin.rest.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives(): Call<List<Live>> {
        CoroutineScope(Dispatchers.IO).launch {
            val lives = retrofitService.getAllLives().execute().body()
            Log.i(ContentValues.TAG,  "RES TEST $lives")
        }
        return retrofitService.getAllLives()
    }

}