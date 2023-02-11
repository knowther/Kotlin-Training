package com.becas.ntt.watchen.data.webclient.model.dto

data class MovieResponseDTO(
    val page: Int,
    val results: List<MovieDTO>,
    val total_pages: Int,
    val total_result: Int
)