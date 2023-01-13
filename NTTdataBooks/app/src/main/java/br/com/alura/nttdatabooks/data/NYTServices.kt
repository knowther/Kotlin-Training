package br.com.alura.nttdatabooks.data

import br.com.alura.nttdatabooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "J24lWr9HhAOiiWcneHdRo4JAkLwMDICx",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}