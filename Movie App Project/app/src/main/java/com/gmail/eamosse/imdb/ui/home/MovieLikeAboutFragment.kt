package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Log.println
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import com.gmail.eamosse.imdb.databinding.FragmentMovieAboutBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters
import kotlinx.android.synthetic.main.fragment_home_second.*
import kotlinx.android.synthetic.main.fragment_movie_about.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MovieLikeAboutFragment : Fragment() {
    val args: MovieAboutFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentMovieAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(homeViewModel) {


            movie.observe(
                viewLifecycleOwner,
                Observer {
                    binding.item = it;
                    BidingAdapters.changeImage(binding.movieImg, it.poster_path);
                }
            )
        }
    }
}

