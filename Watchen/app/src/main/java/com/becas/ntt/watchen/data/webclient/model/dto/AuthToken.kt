package com.becas.ntt.watchen.data.webclient.model.dto

data class AuthToken(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)