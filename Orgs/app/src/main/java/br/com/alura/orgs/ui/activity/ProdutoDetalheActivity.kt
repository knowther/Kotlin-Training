package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.com.alura.orgs.databinding.ActivityProductDetailBinding
import br.com.alura.orgs.model.Produto

class ProdutoDetalheActivity: Activity() {

    private lateinit var bindingDetailProduct: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDetailProduct = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetailProduct.root)
        val data: Intent = this.intent
        if(data.hasExtra("KEY_PRODUCT")){
            val product: Produto? = data.getParcelableExtra("KEY_STUDENT")
            val productTitle = bindingDetailProduct.activityProductDetailProductTitle
            productTitle.text = product?.nome
        }

    }
}