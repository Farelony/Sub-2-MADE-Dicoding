package com.example.tmdb.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.tmdb.databinding.ActivityDetailScreenBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailScreenActivity : AppCompatActivity() {

    companion object{
        const val DETAIL_MOVIE = "detail_movie"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding : ActivityDetailScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"

        setData()
    }

    private fun setData() {
        val detailMovie = intent.getParcelableExtra<Movie>(DETAIL_MOVIE)
        if(detailMovie != null){
            binding.apply{
                Glide.with(binding.imgPosterdetail)
                    .load("https://image.tmdb.org/t/p/w500/${detailMovie.image}")
                    .into(binding.imgPosterdetail)
                Glide.with(binding.imgSampuldetail)
                    .load("https://image.tmdb.org/t/p/w500/${detailMovie.poster}")
                    .into(binding.imgSampuldetail)
                tvJudul.text = detailMovie.title
                tvSinopsis.text = detailMovie.overview
                tvTanggalrilis.text = detailMovie.release_date
                tvUserscore.text = detailMovie.vote_average.toString()
                togglebtnFav.isChecked = detailMovie.isFav
            }
            binding.togglebtnFav.setOnClickListener {
                detailViewModel.setFavMovie(detailMovie,detailMovie.isFav)
            }
        }
    }
}