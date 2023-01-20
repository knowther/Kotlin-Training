package br.com.ntt.mvvm2

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.ntt.mvvm2.databinding.ActivityMainBinding
import br.com.ntt.mvvm2.repositories.MainRepository
import br.com.ntt.mvvm2.ui.adapter.MainAdapter
import br.com.ntt.mvvm2.viewmodel.main.MainViewModel
import br.com.ntt.mvvm2.viewmodel.main.MainViewModelFactory
import br.com.ntt.mvvm2.webclient.RetrofitService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService().liveService

    private val adapter = MainAdapter { live ->
        openLink(live.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setLiveList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()

    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }
}