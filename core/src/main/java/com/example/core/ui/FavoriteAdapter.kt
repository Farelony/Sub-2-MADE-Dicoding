package com.example.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.data.source.remote.network.APIConfig.IMAGE_URL
import com.example.core.databinding.ListItemBinding
import com.example.core.domain.model.Movie

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var listFavMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovies(movies: List<Movie>?){
        if (movies == null)
            return
        listFavMovie.clear()
        listFavMovie.addAll(movies)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding = ListItemBinding.bind(itemView)
        fun bind(data : Movie){
            Glide.with(itemView.context)
                .load(IMAGE_URL + data.poster)
                .into(binding.imgSampul)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listFavMovie[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = listFavMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return  listFavMovie.size
    }
}