package com.becas.ntt.watchen.domain.utils.extensions

import android.content.Context
import android.content.Intent
import java.text.DecimalFormat

fun Context.goTo(
    clazz: Class<*>,
    intent: Intent.() -> Unit = {}
) {
    Intent(this, clazz).apply {
        intent()
        startActivity(this)
    }
}

fun Context.formatUserAvaliation(
    number: Double?
): String{
    val df = DecimalFormat("#.#")
    return df.format(number)
}