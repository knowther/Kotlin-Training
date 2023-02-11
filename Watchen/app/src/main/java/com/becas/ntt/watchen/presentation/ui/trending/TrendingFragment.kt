package com.becas.ntt.watchen.presentation.ui.trending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.becas.ntt.watchen.presentation.ui.MovieDetailActivity
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.databinding.ActivityMovieDetailBinding
import com.becas.ntt.watchen.databinding.FragmentTrendingBinding
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.recyclerview.adapter.MoviesHomeAdapter
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.extensions.goTo
import com.becas.ntt.watchen.presentation.ui.paging.MoviePagingAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
//    lateinit var adapter: MoviePagingAdapter
    private lateinit var viewModel: TrendingViewModel
    private lateinit var binding: FragmentTrendingBinding

    private val repository by lazy{
        MovieRepository()
    }

    private val adapter by lazy {
        MoviePagingAdapter{movie ->
            context?.goTo(MovieDetailActivity::class.java){
                putExtra(AppConstants.MOVIE_ID, movie.id.toString())
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
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configuraRecyclerView()
        viewModel = ViewModelProvider(
            this,
            TrendingViewModelFactory(repository)
        ).get(TrendingViewModel::class.java)
        recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.getTrendingMovies().observe(viewLifecycleOwner){ result ->
                adapter.submitData(lifecycle, result)
            }
        }
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
        binding.recyclerView.adapter = this.adapter
    }

}