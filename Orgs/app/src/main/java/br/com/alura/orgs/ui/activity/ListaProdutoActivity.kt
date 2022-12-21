package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.reciclerview.adapter.ListaProdutosAdapter


class ListaProdutoActivity: AppCompatActivity(), ListaProdutosAdapter.ClickProduto {

    private lateinit var bindingMain: ActivityListaProdutosBinding
    private val adapter = ListaProdutosAdapter(
        context = this,  clickProduto = this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        configuraRecicleView()
        configuraFab()

        var db = AppDataBase.instance(this)

        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    override fun onResume() {
        super.onResume()
        var db = AppDataBase.instance(this)

        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = bindingMain.extendedFab

        fab.setOnClickListener {
            val intent = Intent(this, FormProdutoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraRecicleView() {
        val recyclerView = bindingMain.reciclerView

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        }


    override fun clickProduto(produto: Produto) {
        super.clickProduto(produto)
        val goToDetailProduct: Intent = Intent(this, ProdutoDetalheActivity::class.java).apply {
              putExtra(CHAVE_PRODUTO_ID, produto.id)
          }
            startActivity(goToDetailProduct)
    }
    }





