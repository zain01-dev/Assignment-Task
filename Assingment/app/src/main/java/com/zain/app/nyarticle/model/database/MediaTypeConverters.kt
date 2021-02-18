package com.zain.app.nyarticle.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zain.app.nyarticle.model.articleDataHolder.Media
import java.lang.reflect.Type
import java.util.*


class MediaTypeConverters {
    private var gson = Gson()

    @TypeConverter
    fun stringToMediaList(data: String?): List<Media?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Media?>?>() {}.type
        return gson.fromJson<List<Media?>>(data, listType)
    }

    @TypeConverter
    fun mediaListToString(someObjects: List<Media?>?): String? {
        return gson.toJson(someObjects)
    }

}