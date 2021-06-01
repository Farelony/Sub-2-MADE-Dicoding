package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.data.source.remote.network.APIConfig.IMAGE_URL
import com.example.core.databinding.ListItemBinding
import com.example.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?){
        if (movies.isNullOrEmpty())
            return
        listMovie.clear()
        listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding = ListItemBinding.bind(itemView)
        fun bind(data : Movie){
            Glide.with(itemView.context)
                .load(IMAGE_URL + data.poster)
                .into(binding.imgSampul)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}