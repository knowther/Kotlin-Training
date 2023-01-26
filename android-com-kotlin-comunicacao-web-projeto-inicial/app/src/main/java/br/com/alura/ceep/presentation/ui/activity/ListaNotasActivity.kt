package br.com.alura.ceep.presentation.ui.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import br.com.alura.ceep.domain.utils.CONSTRAINTS
import br.com.alura.ceep.data.database.AppDatabase
import br.com.alura.ceep.databinding.ActivityListaNotasBinding
import br.com.alura.ceep.domain.extensions.vaiPara
import br.com.alura.ceep.domain.repository.NotaRepository
import br.com.alura.ceep.presentation.ui.recyclerview.adapter.ListaNotasAdapter
import br.com.alura.ceep.presentation.viewmodel.ListaNotasViewModel
import br.com.alura.ceep.presentation.viewmodel.MainViewModelFactory
import br.com.alura.ceep.data.webclient.NotaWebClient
import kotlinx.coroutines.launch

//shared preferences

class ListaNotasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaNotasBinding.inflate(layoutInflater)
    }
    lateinit var viewModel: ListaNotasViewModel

    private val adapter by lazy {
        ListaNotasAdapter(this)
    }
    private val repository by lazy{
        NotaRepository(
            AppDatabase.instancia(this).notaDao(),
            NotaWebClient()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(ListaNotasViewModel::class.java)
        binding.refreshHandler.setOnRefreshListener {
            lifecycleScope.launch {
                Log.i(TAG, "TESTE REFRESH")
                repository.sincroniza()
                binding.refreshHandler.isRefreshing = false
            }
        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.notaList.observe(this, Observer {
            Log.i(TAG, "onStart : $it")
            visibilityControl(recycler = CONSTRAINTS.VISIBLE, semNotas = CONSTRAINTS.GONE)
            adapter.setNotaList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            visibilityControl(recycler = CONSTRAINTS.GONE, semNotas = CONSTRAINTS.VISIBLE)
        })
    }
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{
            viewModel.getAllNotas()
        }
    }
    private fun configuraFab() {
        binding.activityListaNotasFab.setOnClickListener {
            Intent(this, FormNotaActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun visibilityControl(recycler: String, semNotas: String){

        if(recycler == CONSTRAINTS.GONE && semNotas == CONSTRAINTS.VISIBLE){
            binding.activityListaNotasRecyclerview.visibility = GONE
            binding.activityListaNotasMensagemSemNotas.visibility = VISIBLE
        }else{
            binding.activityListaNotasMensagemSemNotas.visibility = GONE
            binding.activityListaNotasRecyclerview.visibility = VISIBLE
        }
    }

    private fun configuraRecyclerView() {
        binding.activityListaNotasRecyclerview.adapter = adapter
        adapter.quandoClicaNoItem = { nota ->
            vaiPara(FormNotaActivity::class.java) {
                putExtra(NOTA_ID, nota.id)
            }
        }
    }
}