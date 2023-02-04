package com.becas.ntt.watchen.domain.model

import com.becas.ntt.watchen.data.webclient.model.dto.Genre
import com.becas.ntt.watchen.data.webclient.model.dto.Video
import com.becas.ntt.watchen.data.webclient.model.dto.Videos

data class Movie(
    val backdrop_path: String?,
    val genres: String?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val video: String?,
    val vote_count: Int?
)