package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Discover
import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName


internal data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("revenue")
    val revenue: Int
)


internal fun MovieResponse.toMovie() = Movie(
    id = id,
    budget = budget,
    title = title,
    overview = overview,
    tagline = tagline,
    poster_path = poster_path,
    vote_average = (vote_average * 10).toInt(),
    vote_count = vote_count,
    release_date = release_date,
    revenue = revenue
)