package com.becas.ntt.watchen.presentation.ui.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.becas.ntt.watchen.R
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.domain.utils.AppConstants
import com.becas.ntt.watchen.domain.utils.extensions.tryImageLoader

class MoviePagingAdapter( private val onItemCLicked: (MovieDTO) -> Unit ): PagingDataAdapter<MovieDTO, MoviePagingAdapter.QuoteViewHolder> (
    COMPARATOR) {
    class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieTitle = itemView.findViewById<TextView>(R.id.movie_title)
        val moviePoster = itemView.findViewById<ImageView>(R.id.movie_image)

    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieDTO>(){
            override fun areItemsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDTO, newItem: MovieDTO): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null){
            holder.movieTitle.text = item.title
            holder.moviePoster.tryImageLoader(AppConstants.POSTER_IMAGE_URL + item.poster_path)
            holder.itemView.setOnClickListener {
                onItemCLicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return  QuoteViewHolder(view)
    }
}