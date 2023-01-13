package br.com.alura.nttdatabooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookBodyResponse(
    @Json(name = "results")
    val bookResults: List<BookResultResponse>
)