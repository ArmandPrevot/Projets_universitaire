package com.gmail.eamosse.idbdata.datasources

import android.util.Log
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.DiscoverResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.VideoResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.extensions.parse
import com.gmail.eamosse.idbdata.extensions.safeCall
import com.gmail.eamosse.idbdata.utils.Result

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getDiscover(id: Int, page: Int = 1): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.getDiscover(id, page)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun gettrends(page: Int = 1): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.gettrends(page)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTopRated(page: Int = 1): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.getTopRated(page)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getPopular(page: Int = 1): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.getPopular(page)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getPlaying(page: Int = 1): Result<List<DiscoverResponse.DiscoverItem>> {
        return safeCall {
            val response = service.getPlaying(page)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.results)
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovieById(id:Int): Result<MovieResponse> {
        return safeCall {
            val response = service.getMovieById(id)
            response.parse()
        }
    }

    suspend fun getMovieTrailerById(id:Int): Result<VideoResponse> {
        return safeCall {
            val response = service.getMovieTrailerById(id)
            response.parse()
        }
    }

    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getCategories()
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }
}
