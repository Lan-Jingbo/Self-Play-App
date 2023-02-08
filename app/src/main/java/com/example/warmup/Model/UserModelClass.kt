package com.example.warmup.Model
import android.util.Log

/*data class User( // very first layer
    val users: ArrayList<UserModelClass>
)*/

data class UserModelClass( // G Layer
    val type: String?,
    val teaserText: String?,
    val teaserVideo: TeaserVideoResponse?,
    val showUrl: String?,
    val teaserImage: TeaserImage?,
    val id: String?,
    val description: String?,
    val title: String,
) {
    fun getTeaserVideo() = teaserVideo?.let { teaserVideoResponse ->
        when (teaserVideoResponse) {
            is TeaserVideoResponse.TeaserVideoString -> {
                Log.e("TAG", "teaserVideo string : ${teaserVideoResponse.value}")
                null
            }

            is TeaserVideoResponse.TeaserVideoObject -> {
                Log.e("TAG", "teaserVideo object : hlsUrl : ${teaserVideoResponse.value?.hlsurl}")
                Log.e("TAG", "teaserVideo object : dashUrl : ${teaserVideoResponse.value?.dashurl}")
                teaserVideoResponse.value
            }
        }
    }
}

data class TeaserVideo ( // First Layer
    val preview: Preview?,
    val url: String?,
    val vrcontent: String?,
    val ooyalapcode: String?,
    val duration: Int?,
    val hlsurl: String?,
    val dashurl: String?,
    val houseid: String?,
    val masterrefid: String?,
    val ooyalaid: String?,
    val caption: String?,
    val id: String?,
    val title: String?,
    val ciaKeywords: List<String>?,
)

data class Preview ( // Second Layer
    val id: String?,
    val ratio: String?,
    val alternativeImageUrl: String?,
)

data class TeaserImage ( // First Layer
    val id: String?,
    val ratio: String?,
    val alternativeImageUrl: String?,
)

sealed class TeaserVideoResponse {
    data class TeaserVideoString(var value: String? = null) : TeaserVideoResponse()
    data class TeaserVideoObject(var value: TeaserVideo? = null) : TeaserVideoResponse()
}
