package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Video
import com.google.gson.annotations.SerializedName

internal data class VideoResponse(
    @SerializedName("results")
    val results: List<Video>
) {
    data class VideoItem(
        @SerializedName("key")
        val key: String ?,
    )
}

internal fun VideoResponse.VideoItem.toVideo() = Video(
    key = key,
)
