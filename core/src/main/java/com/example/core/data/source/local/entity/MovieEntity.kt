package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,

    @ColumnInfo(name = "image")
    var image : String = "",

    @ColumnInfo(name = "overview")
    var overview : String = "",

    @ColumnInfo(name = "poster")
    var poster : String = "",

    @ColumnInfo(name = "release_date")
    var release_date : String = "",

    @ColumnInfo(name = "title")
    var title : String = "",

    @ColumnInfo(name = "vote_average")
    var vote_average : Double = 0.0,

    @ColumnInfo(name = "isFav")
    var isFav : Boolean = false
)