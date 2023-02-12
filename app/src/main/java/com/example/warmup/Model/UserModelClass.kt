package com.example.warmup.Model
import android.util.Log
import java.io.Serializable

/*data class User( // very first layer
    val users: ArrayList<UserModelClass>
)*/

data class UserModelClass ( // G Layer
    var type: String?,
    var teaserText: String?,
    var teaserVideo: TeaserVideoResponse?,
    var showUrl: String?,
    var teaserImage: TeaserImage?,
    var id: String?,
    var description: String?,
    var title: String,
) : Serializable {
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
    var preview: Preview?,
    var url: String?,
    var vrcontent: String?,
    var ooyalapcode: String?,
    var duration: Int?,
    var hlsurl: String?,
    var dashurl: String?,
    var houseid: String?,
    var masterrefid: String?,
    var ooyalaid: String?,
    var caption: String?,
    var id: String?,
    var title: String?,
    var ciaKeywords: List<String>?,
) : Serializable

data class Preview ( // Second Layer
    var id: String?,
    var ratio: String?,
    var alternativeImageUrl: String?,
) : Serializable

data class TeaserImage ( // First Layer
    var id: String?,
    var ratio: String?,
    var alternativeImageUrl: String?,
) : Serializable

sealed class TeaserVideoResponse : Serializable {
    data class TeaserVideoString(var value: String? = null) : TeaserVideoResponse()
    data class TeaserVideoObject(var value: TeaserVideo? = null) : TeaserVideoResponse()
}
