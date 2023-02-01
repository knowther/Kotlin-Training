package com.becas.ntt.watchen.utils.extensions

import android.widget.ImageView
import coil.load
import com.becas.ntt.watchen.R

fun ImageView.tryImageLoader(url: String? = null){
    load(url){
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.erro)
    }
}