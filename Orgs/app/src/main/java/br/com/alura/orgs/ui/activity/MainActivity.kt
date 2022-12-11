package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.databinding.ActivityMainBinding
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.reciclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity: AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding

    private lateinit var bindingProduto: ProdutoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        bindingProduto = ProdutoItemBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        val recyclerView = bindingMain.reciclerView
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome = "teste",
                descricao = "teste desc",
                valor = BigDecimal("19.99")
            ),
            Produto(nome = "teste1",
                descricao = "teste desc1",
                valor = BigDecimal("19.99")
            )

        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
        val title = bindingProduto.title
        title.text = "Cesta de Frutas."

    }
}


