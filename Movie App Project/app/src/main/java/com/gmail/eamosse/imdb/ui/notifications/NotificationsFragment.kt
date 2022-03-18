package com.gmail.eamosse.imdb.ui.notifications

import android.app.Notification
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentMovieAboutBinding
import com.gmail.eamosse.imdb.databinding.FragmentNotificationsBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters
import com.gmail.eamosse.imdb.ui.home.*
import kotlinx.android.synthetic.main.fragment_home_second.*
import kotlinx.android.synthetic.main.fragment_movie_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

}
