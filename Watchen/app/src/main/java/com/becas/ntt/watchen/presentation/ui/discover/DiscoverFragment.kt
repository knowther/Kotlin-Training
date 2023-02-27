package com.becas.ntt.watchen.presentation.ui.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.becas.ntt.watchen.presentation.ui.MovieDetailActivity
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.network.MovieWebClient
import com.becas.ntt.watchen.data.webclient.network.NetworkModule
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.databinding.FragmentDiscoverBinding
import com.becas.ntt.watchen.databinding.FragmentTrendingBinding
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.recyclerview.adapter.MoviesHomeAdapter
import com.becas.ntt.watchen.domain.utils.AppConstants.MOVIE_ID
import com.becas.ntt.watchen.domain.utils.extensions.goTo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverFragment: Fragment() {


    private lateinit var viewModel: DiscoverViewModel

    private lateinit var binding: FragmentDiscoverBinding

    private val repository by lazy{
        MovieRepository()
    }

    private val adapter by lazy {
        MoviesHomeAdapter{movie ->
            context?.goTo(MovieDetailActivity::class.java){
                putExtra(MOVIE_ID, movie.id.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container:
        ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configuraRecyclerView()
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setMovieList(it)
        })
        lifecycleScope.launch {
            viewModel.getDiscoverMovies()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
    }

    fun configuraRecyclerView(){
        binding.recyclerViewDiscover.adapter = this.adapter
    }

}