package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.VideoResponse
import com.gmail.eamosse.idbdata.data.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("/3/genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("/3/discover/movie")
    suspend fun getDiscover(@Query("with_genres") withGenres:Int, @Query("page") page:Int = 1): Response<DiscoverResponse>

    @GET("/3/trending/movie/week")
    suspend fun gettrends(@Query("page") page:Int = 1): Response<DiscoverResponse>

    @GET("/3/movie/popular")
    suspend fun getPopular(@Query("page") page:Int = 1): Response<DiscoverResponse>

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(@Query("page") page:Int = 1): Response<DiscoverResponse>

    @GET("/3/movie/now_playing")
    suspend fun getPlaying(@Query("page") page:Int = 1): Response<DiscoverResponse>

    @GET("/3/movie/{movie}")
    suspend fun getMovieById(@Path("movie") movie:Int): Response<MovieResponse>

    @GET("/3/movie/{movie}/videos")
    suspend fun getMovieTrailerById(@Path("movie") movie:Int): Response<VideoResponse>
}