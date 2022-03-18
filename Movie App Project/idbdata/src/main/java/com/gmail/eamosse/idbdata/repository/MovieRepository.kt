package com.gmail.eamosse.idbdata.repository

import android.util.Log
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.extensions.safeCall
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    // Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    // Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Succes -> {
                // save the response in the local database
                local.saveToken(result.data.toEntity())
                // return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getDiscover(id:Int, page:Int = 1): Result<List<Discover>> {
        return when(val result = online.getDiscover(id, page)) {
            is Result.Succes -> {
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrends(page:Int = 1): Result<List<Discover>> {
        return when(val result = online.gettrends(page)) {
            is Result.Succes -> {
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTopRated(page:Int = 1): Result<List<Discover>> {
        return when(val result = online.getTopRated(page)) {
            is Result.Succes -> {
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    suspend fun getPopular(page:Int = 1): Result<List<Discover>> {
        return when(val result = online.getPopular(page)) {
            is Result.Succes -> {
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    suspend fun getPlaying(page:Int = 1): Result<List<Discover>> {
        return when(val result = online.getPlaying(page)) {
            is Result.Succes -> {
                val discover = result.data.map {
                    it.toDiscover()
                }
                Result.Succes(discover)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieById(id:Int): Result<Movie> {
        return when(val result = online.getMovieById(id)) {
            is Result.Succes -> {
                Result.Succes(result.data.toMovie())
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieTrailerById(id:Int): Result<List<Video>> {
        return when(val result = online.getMovieTrailerById(id)) {
            is Result.Succes -> {
                Result.Succes(result.data.results)
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when (val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }
}
