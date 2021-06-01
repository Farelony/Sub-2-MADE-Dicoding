package com.example.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.ui.FavoriteAdapter
import com.example.favorite.databinding.ActivityFavoriteMovieBinding
import com.example.favorite.di.favModule
import com.example.tmdb.detail.DetailScreenActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteMovieBinding
    private val favoriteViewModel : FavoriteViewModel by viewModel()
    private lateinit var favoriteAdapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favModule)

        config()
        setData()
    }

    private fun config() {
        favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = favoriteAdapter
        }
        favoriteAdapter.onItemClick = {
            val moveData = Intent(this, DetailScreenActivity::class.java)
            moveData.putExtra(DetailScreenActivity.DETAIL_MOVIE,it)
            startActivity(moveData)
        }
        supportActionBar?.title = "List Favorite"
    }

    private fun setData(){
        favoriteViewModel.favMovie.observe(this,{ favMovie ->
            if(favMovie != null){
                favoriteAdapter.setMovies(favMovie)
            }
        })
    }
}