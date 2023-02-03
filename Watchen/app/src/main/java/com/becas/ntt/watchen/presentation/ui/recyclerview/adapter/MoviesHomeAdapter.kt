package com.becas.ntt.watchen.presentation.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.becas.ntt.watchen.databinding.MovieItemBinding
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.extensions.tryImageLoader
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO

class MoviesHomeAdapter(private val onItemClicked: (MovieDTO) -> Unit): RecyclerView.Adapter<HomeViewHolder>() {
    private var movies = mutableListOf<MovieDTO>()

    fun setNotaList(notas: List<MovieDTO>){
        this.movies = notas.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onItemClicked)
    }

}

class HomeViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(movie: MovieDTO, onItemClicked: (MovieDTO) -> Unit){
        binding.movieTitle.text = movie.title
        binding.movieImage.tryImageLoader(AppConstants.IMAGE_URL + movie.poster_path)
        itemView.setOnClickListener {
            onItemClicked(movie)
        }
    }
}