package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.dao.ProdutoDAO
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.ui.reciclerview.adapter.ListaProdutosAdapter


class ListaProdutoActivity: AppCompatActivity() {

    private lateinit var bindingMain: ActivityListaProdutosBinding

    private val dao = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(
        context = this, produtos = dao.buscaTodos()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        configuraRecicleView()
        configuraFab()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())

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

        adapter.whenClickItemListener = {
          val goToDetailProduct: Intent = Intent(this, ProdutoDetalheActivity::class.java)
            goToDetailProduct.putExtra("KEY_PRODUCT", it)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}


