package com.becas.ntt.watchen.presentation.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.presentation.ui.MovieDetailActivity
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.MovieWebClient
import com.becas.ntt.watchen.data.webclient.NetworkModule
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.recyclerview.adapter.MoviesHomeAdapter
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.extensions.goTo
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpcomingFragment : Fragment() {

    private lateinit var viewModel: UpcomingViewModel

    private val repository by lazy{
        val api = NetworkModule().tmdbApi()
        MovieRepository(MovieWebClient())
    }

    private val adapter by lazy {
        MoviesHomeAdapter{movie ->
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
        return inflater.inflate(
            R.layout.fragment_trending,
            container,
            false
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configuraRecyclerView()
        viewModel = ViewModelProvider(
            this,
            UpcomingViewModelFactory(repository)
        ).get(UpcomingViewModel::class.java)

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
            viewModel.getUpcomingMovies()
        }
    }

    fun configuraRecyclerView(){
        recycler_view.adapter = this.adapter
    }
}