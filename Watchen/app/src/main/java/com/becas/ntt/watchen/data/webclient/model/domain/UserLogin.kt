package com.becas.ntt.watchen.data.webclient.model.domain

data class UserLogin(
    var username: String = "",
    var password: String ="",
    var request_token: String? = null
)