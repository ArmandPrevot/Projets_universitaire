package com.gmail.eamosse.imdb.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.glide.BidingAdapters
import com.gmail.eamosse.imdb.ui.home.HomeSecondFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_movie_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: HomeViewModel by viewModel();
    private lateinit var likeBtn: Button;
    private lateinit var trendsBtn: Button;
    private lateinit var playingBtn: Button;
    private lateinit var popularBtn: Button;
    private lateinit var topRatedBtn: Button;

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        likeBtn = root.findViewById(R.id.dash_like);
        trendsBtn = root.findViewById(R.id.dash_trends);
        playingBtn = root.findViewById(R.id.dash_playing);
        popularBtn = root.findViewById(R.id.dash_popular);
        topRatedBtn = root.findViewById(R.id.dash_topRated);
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        with(dashboardViewModel) {


            likeBtn.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.dashBoardToMovieLike("");
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

            trendsBtn.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.dashBoardToMovieTrends();
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

            playingBtn.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.navigationPlaying2();
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

            popularBtn.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.dashboardToPopular();
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

            topRatedBtn.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.dashToTopRated();
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

        }
    }
}
