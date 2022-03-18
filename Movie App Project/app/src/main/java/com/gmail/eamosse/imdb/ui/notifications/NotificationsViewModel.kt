package com.gmail.eamosse.imdb.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.eamosse.idbdata.data.Movie

class NotificationsViewModel : ViewModel() {

    private val _notifications: MutableLiveData<MutableList<String>> = MutableLiveData()
    val notifications: LiveData<MutableList<String>>
        get() = _notifications
}