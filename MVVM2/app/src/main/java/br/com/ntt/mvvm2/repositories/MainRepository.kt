package br.com.ntt.mvvm2.repositories

import android.content.ContentValues
import android.util.Log
import br.com.ntt.mvvm2.models.Live
import br.com.ntt.mvvm2.webclient.RetrofitService
import br.com.ntt.mvvm2.webclient.services.LiveService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class MainRepository constructor(private val liveService: LiveService) {

    fun getAllLives(): Call<List<Live>> {
        CoroutineScope(Dispatchers.IO).launch {
            val lives = liveService.getAllLives().execute().body()
            Log.i(ContentValues.TAG,  "RES TEST $lives")
        }
        return liveService.getAllLives()
    }

}