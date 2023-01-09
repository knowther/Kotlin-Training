package br.com.alura.orgs.ui.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.extensions.goTo
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.preferences.dataStore
import br.com.alura.orgs.preferences.usuarioLogadoPreference
import br.com.alura.orgs.ui.reciclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


class ListaProdutoActivity : UserBaseActivity() {

    private lateinit var bindingMain: ActivityListaProdutosBinding
    private val produtoDao by lazy {
        AppDataBase.instance(this).produtoDao()

    }

    private val usuarioDao by lazy {
        AppDataBase.instance(this).usuarioDao()
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

        lifecycleScope.launch {
            launch {
                usuario.filterNotNull()
                    .collect {
                        Log.i("onCreate", "user: $it")
                        buscaProdutoUser(it.username)
                    }
            }
        }
    }


    override fun onResume() {
        super.onResume()

//       val handler = CoroutineExceptionHandler{coroutineContext, throwable ->
//            Log.i("onResume: ", "throw $throwable")
//            Toast.makeText(this@ListaProdutoActivity,
//                "Ocorreu um erro",
//                Toast.LENGTH_SHORT).show()
//        }

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Paused")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "DESTROYED")
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
            lifecycleScope.launch {
                produtoDao.deleta(it)
//                adapter.atualiza(produtoDao.buscaTodos())
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            when (item.itemId) {
                R.id.submenu_filter_nome_ASC -> adapter.atualiza(produtoDao.orderByNameASC())
                R.id.submenu_filter_nome_DESC -> adapter.atualiza(produtoDao.orderByNameDESC())
                R.id.submenu_filter_descricao_ASC -> adapter.atualiza(produtoDao.orderByDescASC())
                R.id.submenu_filter_descricao_DESC -> adapter.atualiza(produtoDao.orderByDescDESC())
                R.id.submenu_filter_valor_ASC -> adapter.atualiza(produtoDao.orderByValueASC())
                R.id.submenu_filter_valor_DESC -> adapter.atualiza(produtoDao.orderByValueDESC())
//                R.id.submenu_filter_sem_ordenacao -> buscaProdutoUser()
                R.id.exit_menu -> {
                    deslogarUser()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private suspend fun buscaProdutoUser(usuarioId: String) {
        produtoDao.buscaTodosPorUser(usuarioId).collect { produtos ->
            adapter.atualiza(produtos)
        }
    }


}





