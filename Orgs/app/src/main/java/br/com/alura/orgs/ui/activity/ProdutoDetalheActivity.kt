package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.databinding.ActivityProductDetailBinding
import br.com.alura.orgs.extensions.tryImageLoader
import br.com.alura.orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ProdutoDetalheActivity: AppCompatActivity() {


    private val produtoDao by lazy{
        AppDataBase.instance(this).produtoDao()

    }

    private var produtoId: Long = 0L
    private var produto: Produto? = null

    private lateinit var bindingDetailProduct: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Detalhe do produto")
        bindingDetailProduct = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetailProduct.root)
        tryLoadProduct()
    }

    override fun onResume() {
        super.onResume()
            produto = produtoDao.findById(produtoId)!!
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }


    // método que serve para vincular o menu ao app bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //método que é chamado quando algum item do menu é clicado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(::produto != null){
            //filtrar ações dado determinado valor que no caso depende do menu item selecionado
            when(item.itemId){
                //caso o edit for pressionado
                R.id.menu_product_detail_edit -> {

                    Intent(this, FormProdutoActivity::class.java).apply {
                        putExtra(CHAVE_PRODUTO_ID, produtoId)
                        startActivity(this)
                    }
                }
                //caso e delete for pressionado
                R.id.menu_product_detail_delete -> {
                    produto?.let {
                        produtoDao.deleta(it)
                    }

                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //método que carrega os dados da activity anterior via extra
    private fun tryLoadProduct() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
        }


    private fun preencheCampos(product: Produto?) {
        val productImage = bindingDetailProduct.imageView2
        val productTitle = bindingDetailProduct.activityProductDetailProductTitle
        val productDect = bindingDetailProduct.activityProductDetailProductDetail
        val productPrice = bindingDetailProduct.activityProductDetailProductPrice

        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val valorEmModeda: String = formatter.format(product?.valor)

        productTitle.text = product?.nome
        productDect.text = product?.descricao
        productPrice.text = valorEmModeda
        productImage.tryImageLoader(product?.image)
    }
}