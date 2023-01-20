package br.com.ntt.mvvm2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ntt.mvvm2.R
import br.com.ntt.mvvm2.databinding.LiveItemBinding
import br.com.ntt.mvvm2.models.Live
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainAdapter(private val onItemClicked: (Live) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private var lives = mutableListOf<Live>()

    fun setLiveList(lives: List<Live>) {

        this.lives = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LiveItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = lives[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewHolder(val binding: LiveItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(live: Live, onItemClicked: (Live) -> Unit) {

        binding.liveAutor.text = live.title
        binding.liveAutor.text = live.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(binding.liveImage)

        itemView.setOnClickListener {
            onItemClicked(live)
        }

    }

}