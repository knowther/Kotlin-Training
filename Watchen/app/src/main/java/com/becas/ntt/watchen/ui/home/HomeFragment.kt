package com.becas.ntt.watchen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.NetworkModule
import com.becas.ntt.watchen.repository.MovieRepository
import com.becas.ntt.watchen.ui.recyclerview.adapter.MoviesHomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment: Fragment() {


    private lateinit var viewModel: HomeViewModel

    private val repository by lazy{
        val api = NetworkModule().tmdbApi()
        MovieRepository(api)
    }

    private val adapter by lazy {
        MoviesHomeAdapter()
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
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configuraRecyclerView()
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(repository)
        ).get(HomeViewModel::class.java)

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setNotaList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch{
            viewModel.getTrendingMovies()
        }
    }

    fun configuraRecyclerView(){
        recycler_view.adapter = this.adapter
    }

}