package br.com.alura.orgs.ui.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.reciclerview.adapter.ListaProdutosAdapter


class ListaProdutoActivity: AppCompatActivity(){

    private lateinit var bindingMain: ActivityListaProdutosBinding
    private val produtoDao by lazy{
        AppDataBase.instance(this).produtoDao()

    }
    private val adapter = ListaProdutosAdapter(
        context = this
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)


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

        adapter.quandoClicaNoItem = {

            val intent = Intent(
                this,
                ProdutoDetalheActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
        adapter.quandoClicaEmEditar = {
            val intent = Intent(
                this,
                FormProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
        adapter.quandoClicaEmRemover = {
            produtoDao.deleta(it)
            adapter.atualiza(produtoDao.buscaTodos())
        }

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.submenu_filter_nome_ASC -> adapter.atualiza(produtoDao.orderByNameASC())
            R.id.submenu_filter_nome_DESC -> adapter.atualiza(produtoDao.orderByNameDESC())
            R.id.submenu_filter_descricao_ASC -> adapter.atualiza(produtoDao.orderByDescASC())
            R.id.submenu_filter_descricao_DESC -> adapter.atualiza(produtoDao.orderByDescDESC())
            R.id.submenu_filter_valor_ASC -> adapter.atualiza(produtoDao.orderByValueASC())
            R.id.submenu_filter_valor_DESC -> adapter.atualiza(produtoDao.orderByValueDESC())
            R.id.submenu_filter_sem_ordenacao -> adapter.atualiza(produtoDao.buscaTodos())
        }
        return super.onOptionsItemSelected(item)
    }




}





