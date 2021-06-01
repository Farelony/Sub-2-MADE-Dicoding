package com.example.tmdb.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.MovieAdapter
import com.example.tmdb.databinding.ActivityHomeScreenBinding
import com.example.tmdb.detail.DetailScreenActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeScreenBinding
    private val movieViewModel : MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        config()
        setData()
        binding.fabFavorite.setOnClickListener {
            val uri = Uri.parse("favoriteapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }
    }

    private fun config() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = movieAdapter
        }
        movieAdapter.onItemClick = {
            val moveData = Intent(this, DetailScreenActivity::class.java)
            moveData.putExtra(DetailScreenActivity.DETAIL_MOVIE,it)
            startActivity(moveData)
        }
        supportActionBar?.title = "List Movie Popular"
    }

    private fun setData() {
        movieViewModel.latestMovie.observe(this, { movie ->
            if(movie != null){
                when(movie){
                    is Resource.Loading -> binding.pbMovies.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMovies.visibility = View.INVISIBLE
                        movieAdapter.setMovies(movie.data)
                    }
                    is Resource.Error ->{
                        binding.pbMovies.visibility = View.INVISIBLE
                        Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}