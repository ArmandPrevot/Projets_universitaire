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
import com.gmail.eamosse.imdb.databinding.FragmentTrendingBinding
import kotlinx.android.synthetic.main.fragment_home_second.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class PlayingFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentTrendingBinding

    companion object {
        const val ARG_GENRE = "genre"
    }

    var page: Int = 1
    var scrolled = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getPlaying(page)

                    discoverList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if (!recyclerView.canScrollVertically(1)) {
                                val thread = Thread {
                                    Thread.sleep(1_000)
                                    binding.scrollview.smoothScrollBy(0, scrolled);
                                }.start();
                                appendPlaying(page++)
                            }
                        }

                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            scrolled += dy;
                        }
                    })
                }
            )

            discoveries.observe(
                viewLifecycleOwner,
                Observer {
                    binding.discoverList.adapter = DiscoverAdapter(it) {
                        val action = PlayingFragmentDirections.playingToAbout(it.id.toString());
                        NavHostFragment.findNavController(this@PlayingFragment).navigate(action)
                    }

                }
            )
        }
    }

}

