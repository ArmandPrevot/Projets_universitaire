package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _discovers: MutableLiveData<List<Discover>> = MutableLiveData()
    val discoveries: LiveData<List<Discover>>
        get() = _discovers

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    private val _likedMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData()
    val likedMovies: LiveData<MutableList<Movie>>
        get() = _likedMovies

    private val _videos: MutableLiveData<List<Video>> = MutableLiveData();
    val videos: LiveData<List<Video>>
    get() = _videos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun addLikedMovie(movie: Movie) {
        var m = mutableListOf<Movie>();
        if (_likedMovies.value != null) {
            m = _likedMovies.value!!;
        }
        m.add(movie);
        _likedMovies.postValue(m);
        //Log.e("test:!", likedMovies.value?.get(0)?.title);
    }

    fun getDiscover(id: Int, pagination:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getDiscover(id, pagination)) {
                is Result.Succes -> {
                    _discovers.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getTrends(pagination:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrends(pagination)) {
                is Result.Succes -> {
                    _discovers.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getTopRated(pagination:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTopRated(pagination)) {
                is Result.Succes -> {
                    _discovers.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getPopular(pagination:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopular(pagination)) {
                is Result.Succes -> {
                    _discovers.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getPlaying(pagination:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPlaying(pagination)) {
                is Result.Succes -> {
                    _discovers.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovieById(id: Int)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieById(id)) {
                is Result.Succes -> {
                    _movie.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovieTrailerById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieTrailerById(id)) {
                is Result.Succes -> {
                    _videos.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun appendDiscovers(id: Int, page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getDiscover(id, page)) {
                is Result.Succes -> {
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<Discover>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun appendtrends(page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrends(page)) {
                is Result.Succes -> {
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<Discover>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun appendTopRated(page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTopRated(page)) {
                is Result.Succes -> {
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<Discover>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun appendPopular(page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopular(page)) {
                is Result.Succes -> {
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<Discover>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun appendPlaying(page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPlaying(page)) {
                is Result.Succes -> {
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<Discover>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}