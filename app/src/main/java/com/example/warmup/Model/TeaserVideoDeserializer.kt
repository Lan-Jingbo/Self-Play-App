package com.example.warmup.Model

import com.example.warmup.utils.deserializeObject
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Deserialize [TeaserVideo] manually
 */
class TeaserVideoDeserializer: JsonDeserializer<TeaserVideoResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    )=
        when {
            json?.isJsonObject == true -> {
                TeaserVideoResponse.TeaserVideoObject(
                    json.asJsonObject.deserializeObject(context!!)
                )
            }

            json?.isJsonPrimitive == true && json.asJsonPrimitive.isString ->
                TeaserVideoResponse.TeaserVideoString(json.asJsonPrimitive.asString)

            else -> throw IllegalStateException("Cannot parse $json")
        }
}