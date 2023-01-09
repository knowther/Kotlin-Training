package br.com.alura.orgs.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import br.com.alura.orgs.ui.activity.FormProdutoActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun Context.goTo(clazz: Class<*>, intent: Intent.() -> Unit = {}) {
    Intent(this, clazz).apply {
        intent()
        startActivity(this)
    }
}

fun Context.toast(msg: String){
    Toast.makeText(
        this,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}