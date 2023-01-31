package com.example.warmup.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

inline fun <reified T> JsonElement.deserializeObject(
    context: JsonDeserializationContext?
): T? {
    return try {
        context?.deserialize<T>(this, object : TypeToken<T>() {}.type)
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.getString(key: String): String? {
    return get(key)?.let {
        if (it.isJsonNull) {
            null
        } else {
            it.asString
        }
    }
}

fun JsonObject.getElement(memberName: String): JsonElement? {
    return get(memberName)?.let {
        if (it.isJsonNull) {
            null
        } else {
            it
        }
    }
}

fun JsonObject.getJsonObject(memberName: String): JsonObject? {
    return get(memberName)?.let {
        if (it.isJsonNull) {
            null
        } else {
            it as JsonObject
        }
    }
}