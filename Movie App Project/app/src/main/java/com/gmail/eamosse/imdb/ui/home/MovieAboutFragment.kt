package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentMovieAboutBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.fragment_home_second.*
import kotlinx.android.synthetic.main.fragment_movie_about.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MovieAboutFragment : Fragment() {
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
            token.observe(
                viewLifecycleOwner,
                Observer {
                    if (args.movieId.length > 0) {
                        getMovieById(id = args.movieId.toInt())
                    } else {
                        getMovieById(id = Random.nextInt(40000, 60000))
                    }
                }
            )

            videos.observe(viewLifecycleOwner, Observer {
                if (it.size > 0) {
                        youtube_player_view.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                        override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                           youTubePlayer.loadVideo(it[0].key!!, 0f)
                        }
                    })
                } else {
                    youtube_player_view.visibility = View.INVISIBLE;
                }
            })

            error.observe(
                viewLifecycleOwner,
                Observer {
                    getMovieById(id = Random.nextInt(40000, 60000))
                }
            )

            movie.observe(
                viewLifecycleOwner,
                Observer {
                    binding.item = it
                    getMovieTrailerById(it.id!!)
                    BidingAdapters.changeImage(binding.movieImg, it.poster_path)
                }
            )
        }
    }
}
